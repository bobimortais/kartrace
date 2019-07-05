package com.test.kartrace.service;

import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.entity.LapInfo;
import com.test.kartrace.util.FileProcessor;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProcessRaceInfoService
{
    public static void processRaceResults(String filePath) throws Exception
    {
        List<String> fileLines = FileProcessor.getLinesFromFile(filePath);
        List<LapInfo> lapInfoList = convertLinesToLapInfo(fileLines);
        List<DriverInfo> driverInfoList = getDriversInfo(lapInfoList);
        showRaceResults(driverInfoList);
    }

    private static List<LapInfo> convertLinesToLapInfo(List<String> fileLines) throws Exception
    {
        List<LapInfo> lapInfoList = new ArrayList<LapInfo>();
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

    private static List<DriverInfo> getDriversInfo(List<LapInfo> lapInfoList)
    {
        List<DriverInfo> driverInfoList = new ArrayList<DriverInfo>();
        return driverInfoList;
    }

    private static void showRaceResults(List<DriverInfo> driverInfoList)
    {

    }
}
