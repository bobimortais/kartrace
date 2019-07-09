package com.test.kartrace;

import com.test.kartrace.service.ProcessRaceInfoService;
import com.test.kartrace.util.FileProcessor;

import java.util.List;

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
            List<String> fileLines = FileProcessor.getLinesFromFile(filePath);
            ProcessRaceInfoService.processRaceResults(fileLines);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
