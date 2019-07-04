package com.test.kartrace.service;

import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.entity.LapInfo;
import com.test.kartrace.util.FileProcessor;
import java.util.ArrayList;
import java.util.List;

public class ProcessRaceInfoService
{
    public static void processRaceResults(String filePath)
    {
        List<String> fileLines = FileProcessor.getLinesFromFile(filePath);
        List<LapInfo> lapInfoList = convertLinesToLapInfo(fileLines);
        List<DriverInfo> driverInfoList = getDriversInfo(lapInfoList);
        showRaceResults(driverInfoList);
    }

    private static List<LapInfo> convertLinesToLapInfo(List<String> fileLines)
    {
        List<LapInfo> lapInfoList = new ArrayList<LapInfo>();
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
