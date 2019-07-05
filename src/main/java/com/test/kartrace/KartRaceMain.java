package com.test.kartrace;

import com.test.kartrace.service.ProcessRaceInfoService;

public class KartRaceMain
{
    public static void main(String[] args)
    {
        try
        {
            //Receive the file to be processed as argument
            String filePath = args[0];
            /**Call the ProcessRaceInfoService to process the file and show the
            race result*/
            ProcessRaceInfoService.processRaceResults(filePath);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
