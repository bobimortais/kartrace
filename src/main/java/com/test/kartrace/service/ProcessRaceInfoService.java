package com.test.kartrace.service;

import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.entity.LapInfo;
import com.test.kartrace.util.FileProcessor;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

/**
 * Service class to process the input file and show the race results
 */
public class ProcessRaceInfoService
{
    public static void processRaceResults(String filePath) throws Exception
    {
        List<String> fileLines = FileProcessor.getLinesFromFile(filePath);
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("m:ss.SSS");
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        fileLines.remove(0);

        for(String line : fileLines)
        {
            line = line.trim().replaceAll("\\s+", " ");
            String[] values = line.split(" ");
            LapInfo lapInfo = new LapInfo();
            lapInfo.setLapTimeStamp(LocalTime.parse(values[0]));
            lapInfo.setDriverId(Integer.parseInt(values[1]));
            lapInfo.setDriverName(values[3]);
            lapInfo.setLapNumber(Integer.parseInt(values[4]));
            dateFormat.parse(values[5]).getTime();
            lapInfo.setLapTime(new Timestamp(dateFormat.parse(values[5]).getTime()));
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
        int position = 1;

        for(LapInfo lapInfo : lapInfoList)
        {
           if (driverInfoList.stream().anyMatch(li -> li.getDriverId() == lapInfo.getDriverId()))
           {
               for(DriverInfo driverInfo : driverInfoList)
               {
                   if(driverInfo.getDriverId() == lapInfo.getDriverId())
                   {
                       driverInfo.setCompletedLaps(driverInfo.getCompletedLaps() + 1);
                       //driverInfo.setTotalRaceTime();
                       //driverInfo.setBestLap(lapInfo.getLapTime());
                       driverInfo.setAvgSpeed((driverInfo.getAvgSpeed() + lapInfo.getAvgLapSpeed()));
                       //driverInfo.setGapToLeader();
                       driverInfo.setRaceBestLap(false);
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
               //driverInfo.setBestLap(lapInfo.getLapTime());
               driverInfo.setAvgSpeed(lapInfo.getAvgLapSpeed());
               //driverInfo.setGapToLeader();
               driverInfo.setRaceBestLap(false);
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
        String leftAlignFormat = "| %-7s | %-6s | %-14s | %-6s | %-12s | %-23s | %-16s | %-16s | %-11s |%n";

        System.out.format("+---------+--------+----------------+--------+--------------+-------------------------+------------------+------------------+-------------+%n");
        System.out.format("| Posicao | Codigo | Piloto         | Voltas | Melhor Volta | Melhor Volta da Corrida | Velocidade Media | Tempo para lider | Tempo Total |%n");
        System.out.format("+---------+--------+----------------+--------+--------------+-------------------------+------------------+------------------+-------------+%n");

        for(DriverInfo driver : driversResults)
        {
            System.out.format(leftAlignFormat,
                            driver.getDriverPosition(),
                            driver.getDriverId(),
                            driver.getDriverName(),
                            driver.getCompletedLaps(),
                            driver.getBestLap(),
                            "x",
                            driver.getAvgSpeed() / driver.getCompletedLaps(),
                            0,
                            0);
        }
        System.out.format("+---------+--------+----------------+--------+--------------+-------------------------+------------------+------------------+-------------+%n");
    }
}
