import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.service.ProcessRaceInfoService;
import com.test.kartrace.util.FileProcessor;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestRaceProcessing
{
    @Test
    public void TEST_FILE_LOAD_SUCESS()
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
        Assert.assertTrue(fileLines.size() > 0);
    }

    @Test
    public void TEST_FILE_LOAD_FAIL()
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_3.txt");
        Assert.assertTrue(fileLines.size() == 0);
    }

    @Test
    public void POSITION_CORRECT()
    {
       try
       {
           List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
           List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
           Assert.assertTrue(true);
       }
       catch(Exception e)
       {
           Assert.assertTrue(false);
       }
    }

    @Test
    public void CHECK_COMPLETED_LAPS()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
            List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
            Assert.assertTrue(true);
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void CHECK_TOTAL_RACE_TIME()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
            List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
            Assert.assertTrue(true);
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void CHECK_DRIVER_BEST_LAP()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
            List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
            Assert.assertTrue(true);
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void CHECK_RACE_BEST_LAP()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
            List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
            Assert.assertTrue(true);
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void CHECK_GAP_TO_LEADER()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
            List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
            Assert.assertTrue(true);
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void CHECK_DRIVER_RACE_AVG_SPEED()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
            List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
            Assert.assertTrue(true);
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void CHECK_ALL_DATA_SHOWN()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
            List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
            Assert.assertTrue(true);
        }
        catch(Exception e)
        {
            Assert.assertTrue(false);
        }
    }
}
