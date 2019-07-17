package com.test.kartrace;

import com.test.kartrace.display.DisplayRaceResults;
import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.service.ProcessRaceInfoService;
import com.test.kartrace.util.FileProcessor;
import org.apache.log4j.Logger;
import java.nio.file.NoSuchFileException;
import java.text.ParseException;
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
        catch(NoSuchFileException e)
        {
            logger.error("The file to be processed was not found", e);
        }
        catch(ParseException e)
        {
            logger.error("Error proccessing data from input file", e);
        }
        catch(Exception e)
        {
            logger.error("An error occurred when processing the request", e);
        }
    }
}
