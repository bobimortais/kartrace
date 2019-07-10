package com.test.kartrace.service;

import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.entity.LapInfo;
import org.apache.log4j.Logger;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service class to process the input file and show the race results
 */
public class ProcessRaceInfoService
{
    private ProcessRaceInfoService()
    {

    }

    private static final Logger logger = Logger.getLogger(ProcessRaceInfoService.class);

    public static List<DriverInfo> processRaceResults(List<String> fileLines) throws Exception
    {
        List<LapInfo> lapInfoList = convertLinesToLapInfo(fileLines);
        getDriversResults(lapInfoList);
        return getDriversResults(lapInfoList);
    }

    /**
     * Method to convert the lines from the input file into LapInfo object
     * @param fileLines
     * @return List<LapInfo>
     * @throws Exception
     */
    private static List<LapInfo> convertLinesToLapInfo(List<String> fileLines) throws Exception
    {
        List<LapInfo> lapInfoList = new ArrayList<>();
        SimpleDateFormat lapTimeDateFormat = new SimpleDateFormat("mm:ss.SSS");
        SimpleDateFormat lapTimeStampDateFormat = new SimpleDateFormat("hh:mm:ss.SSS");
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        fileLines.remove(0);

        for(String line : fileLines)
        {
            line = line.trim().replaceAll("\\s+", " ");
            String[] values = line.split(" ");
            LapInfo lapInfo = new LapInfo();
            lapInfo.setLapTimeStamp(new Timestamp(lapTimeStampDateFormat.parse(values[0]).getTime()));
            lapInfo.setDriverId(Integer.parseInt(values[1]));
            lapInfo.setDriverName(values[3]);
            lapInfo.setLapNumber(Integer.parseInt(values[4]));
            lapInfo.setLapTime(new Timestamp(lapTimeDateFormat.parse(values[5]).getTime()));
            lapInfo.setAvgLapSpeed(format.parse(values[6]).doubleValue());
            lapInfoList.add(lapInfo);
        }
        return lapInfoList;
    }

    /**
     * Method to calculate drivers results based on the laps info
     * @param lapInfoList
     * @return List<DriverInfo>
     */
    private static List<DriverInfo> getDriversResults(List<LapInfo> lapInfoList)
    {
        List<DriverInfo> driverInfoList = new ArrayList<>();
        /**Ordering the list of laps by the lap number on descending order and but the lap timestamp to
         * to get the correct finishing order
         */
        lapInfoList.sort(Comparator.comparing(LapInfo::getLapNumber).reversed().thenComparing(LapInfo::getLapTimeStamp));
        //Getting the best lap of the race by ordering the list of laps by the lap time on ascending order
        Optional<LapInfo> bestLapOptional = lapInfoList.stream().sorted(Comparator.comparing(LapInfo::getLapTime)).findFirst();
        LapInfo bestLap = bestLapOptional.get();
        LapInfo leaderLastLap = lapInfoList.get(0);

        //Creating a Map to group the laps by driver ID
        LinkedHashMap<Long, List<LapInfo>> mapLapsByDriver = new LinkedHashMap<>();
        for(LapInfo lap : lapInfoList)
        {
            mapLapsByDriver.computeIfAbsent(lap.getDriverId(), ll -> new ArrayList<LapInfo>());
            mapLapsByDriver.get(lap.getDriverId()).add(lap);
        }

        int position = 0;
        for(Iterator it = mapLapsByDriver.keySet().iterator(); it.hasNext();)
        {
            Long driverId = (Long) it.next();
            List<LapInfo> laps = mapLapsByDriver.get(driverId);
            DriverInfo driverInfo = new DriverInfo();
            driverInfo.setDriverId(driverId);
            driverInfo.setDriverName(laps.get(0).getDriverName());
            driverInfo.setBestLap(laps.get(0).getLapTime());
            driverInfo.setTotalRaceTime(new Date(0));
            driverInfo.setDriverPosition(position++);
            driverInfo.setGapToLeader(new Date(laps.get(0).getLapTimeStamp().getTime() - leaderLastLap.getLapTimeStamp().getTime()));

            for(LapInfo lap : laps)
            {
                driverInfo.setCompletedLaps(driverInfo.getCompletedLaps() + 1);
                if(lap.getLapTime().getTime() < driverInfo.getBestLap().getTime())
                    driverInfo.setBestLap(lap.getLapTime());

                driverInfo.setAvgSpeed((driverInfo.getAvgSpeed() + lap.getAvgLapSpeed()));
                Date totalRaceTime = new Date(driverInfo.getTotalRaceTime().getTime() + lap.getLapTime().getTime());
                driverInfo.setTotalRaceTime(totalRaceTime);

                if(lap.getLapTime().getTime() == bestLap.getLapTime().getTime())
                    driverInfo.setRaceBestLap(true);
            }
            driverInfoList.add(driverInfo);
        }
        return driverInfoList;
    }
}
