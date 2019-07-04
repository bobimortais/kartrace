package com.test.kartrace;

import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.service.ProcessRaceInfoService;

import java.util.List;

public class KartRaceMain
{
    public static void main(String[] args)
    {
        try
        {
            String filePath = args[0];
            ProcessRaceInfoService.processRaceResults(filePath);
        }
        catch(Exception e)
        {

        }
    }
}
