package com.test.kartrace.service;

import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.entity.LapInfo;
import com.test.kartrace.util.FileProcessor;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service class to process the input file and show the race results
 */
public class ProcessRaceInfoService
{
    public static void processRaceResults(List<String> fileLines) throws Exception
    {
        List<LapInfo> lapInfoList = convertLinesToLapInfo(fileLines);
        List<DriverInfo> driversResults = getDriversResults(lapInfoList);
        showRaceResults(driversResults);
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
        List<DriverInfo> driverInfoList = new ArrayList<DriverInfo>();
        lapInfoList.sort(Comparator.comparing(LapInfo::getLapNumber).reversed().thenComparing(LapInfo::getLapTimeStamp));
        Optional<LapInfo> bestLapOptional = lapInfoList.stream().sorted(Comparator.comparing(LapInfo::getLapTime)).findFirst();
        LapInfo bestLap = bestLapOptional.get();

        int position = 1;
        LapInfo leaderLastLap = lapInfoList.get(0);

        for(LapInfo lapInfo : lapInfoList)
        {
           if (driverInfoList.stream().anyMatch(li -> li.getDriverId() == lapInfo.getDriverId()))
           {
               for(DriverInfo driverInfo : driverInfoList)
               {
                   if(driverInfo.getDriverId() == lapInfo.getDriverId())
                   {
                       driverInfo.setCompletedLaps(driverInfo.getCompletedLaps() + 1);

                       if(lapInfo.getLapTime().getTime() < driverInfo.getBestLap().getTime())
                            driverInfo.setBestLap(lapInfo.getLapTime());

                       driverInfo.setAvgSpeed((driverInfo.getAvgSpeed() + lapInfo.getAvgLapSpeed()));
                       Date totalRaceTime = new Date(driverInfo.getTotalRaceTime().getTime() + lapInfo.getLapTime().getTime());
                       driverInfo.setTotalRaceTime(totalRaceTime);

                       if(lapInfo.getLapTime().getTime() == bestLap.getLapTime().getTime())
                           driverInfo.setRaceBestLap(true);
                   }
               }
           }
           else
           {
               DriverInfo driverInfo = new DriverInfo();
               driverInfo.setDriverId(lapInfo.getDriverId());
               driverInfo.setDriverName(lapInfo.getDriverName());
               driverInfo.setCompletedLaps(1);
               driverInfo.setDriverPosition(position);
               driverInfo.setTotalRaceTime(lapInfo.getLapTime());
               driverInfo.setBestLap(lapInfo.getLapTime());
               driverInfo.setAvgSpeed(lapInfo.getAvgLapSpeed());
               driverInfo.setGapToLeader(new Date(lapInfo.getLapTimeStamp().getTime() - leaderLastLap.getLapTimeStamp().getTime()));

               if(lapInfo.getLapTime().getTime() == bestLap.getLapTime().getTime())
                   driverInfo.setRaceBestLap(true);

               driverInfoList.add(driverInfo);
               position++;
           }
        }

        return driverInfoList;
    }

    /**
     * Method to show the race results
     * @param driversResults - List with the results for each driver
     */
    private static void showRaceResults(List<DriverInfo> driversResults)
    {
        String leftAlignFormat = "| %-7s | %-6s | %-14s | %-6s | %-12s | %-16s | %-16s | %-11s |%n";
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss:SSS");

        System.out.format("+---------+--------+----------------+--------+--------------+------------------+------------------+-------------+%n");
        System.out.format("| Posicao | Codigo | Piloto         | Voltas | Melhor Volta | Velocidade Media | Tempo para lider | Tempo Total |%n");
        System.out.format("+---------+--------+----------------+--------+--------------+------------------+------------------+-------------+%n");

        for(DriverInfo driver : driversResults)
        {
            System.out.format(leftAlignFormat,
                            driver.getDriverPosition(),
                            driver.getDriverId(),
                            driver.getDriverName(),
                            driver.getCompletedLaps(),
                            driver.isRaceBestLap() ? dateFormat.format(driver.getBestLap()) + "*" : dateFormat.format(driver.getBestLap()),
                            String.format("%.3f", driver.getAvgSpeed() / driver.getCompletedLaps()),
                            dateFormat.format(driver.getGapToLeader()),
                            dateFormat.format(driver.getTotalRaceTime()));
        }
        System.out.format("+---------+--------+----------------+--------+--------------+------------------+------------------+-------------+%n");
        System.out.println("*Melhor volta da corrida");
    }
}
