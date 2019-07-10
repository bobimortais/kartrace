package com.test.kartrace;

import com.test.kartrace.display.DisplayRaceResults;
import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.service.ProcessRaceInfoService;
import com.test.kartrace.util.FileProcessor;
import org.apache.log4j.Logger;

import java.util.List;

public class KartRaceMain
{
    private static final Logger logger = Logger.getLogger(KartRaceMain.class);

    public static void main(String[] args)
    {
        try
        {
            //Receive the file to be processed as argument
            String filePath = args[0];
            /**Call the ProcessRaceInfoService to process the file and show the
            race result*/
            List<String> fileLines = FileProcessor.getLinesFromFile(filePath);
            List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
            DisplayRaceResults.showRaceResults(driversInfo);
        }
        catch(Exception e)
        {
            logger.error("", e);
        }
    }
}
