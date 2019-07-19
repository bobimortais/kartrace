package com.test.kartrace.service;

import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.entity.LapInfo;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
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

    public static List<DriverInfo> processRaceResults(List<String> fileLines) throws ParseException
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
    private static List<LapInfo> convertLinesToLapInfo(List<String> fileLines) throws ParseException
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

        int position = 1;
        for(Iterator it = mapLapsByDriver.keySet().iterator(); it.hasNext();)
        {
            Long driverId = (Long) it.next();
            List<LapInfo> laps = mapLapsByDriver.get(driverId);
            DriverInfo driverInfo = new DriverInfo(driverId, laps.get(0).getDriverName());
            driverInfo.setDriverPosition(position++);
            driverInfo.setGapToLeader(new Date(laps.get(0).getLapTimeStamp().getTime() - leaderLastLap.getLapTimeStamp().getTime()));

            for(LapInfo lap : laps)
            {
                driverInfo.incrementCompletedLaps();
                if(lap.getLapTime().getTime() < driverInfo.getBestLap().getTime())
                    driverInfo.setBestLap(lap.getLapTime());

                driverInfo.setTotalSpeed((driverInfo.getTotalSpeed() + lap.getAvgLapSpeed()));
                driverInfo.addRaceTime(lap.getLapTime());
                //Laps could have the same lap time
                if(lap.getLapTime().getTime() == bestLap.getLapTime().getTime())
                    driverInfo.setRaceBestLap(true);
            }
            driverInfoList.add(driverInfo);
        }
        return driverInfoList;
    }
}
