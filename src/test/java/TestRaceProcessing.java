import com.test.kartrace.entity.DriverInfo;
import com.test.kartrace.service.ProcessRaceInfoService;
import com.test.kartrace.util.FileProcessor;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestRaceProcessing
{
    private static final SimpleDateFormat lapTimeDateFormat = new SimpleDateFormat("mm:ss.SSS");
    @Test
    public void TEST_FILE_LOAD_SUCESS()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
            Assert.assertTrue(fileLines.size() > 0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TEST_DIFFERENT_FILE_LOAD_SUCESS()
    {
        try
        {
            List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_2.txt");
            Assert.assertTrue(fileLines.size() > 0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test(expected = NoSuchFileException.class)
    public void TEST_FILE_LOAD_FAIL() throws IOException
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_3.txt");
    }

    @Test(expected = ParseException.class)
    public void TEST_INVALID_VALUE_ON_FILE() throws Exception
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_4.txt");
        List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
    }

    @Test
    public void POSITION_CORRECT()
    {
       try
       {
           List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
           List<DriverInfo> driversInfo = ProcessRaceInfoService.processRaceResults(fileLines);
           DriverInfo driverInfo = driversInfo.get(0);
           Assert.assertTrue(driverInfo.getDriverId() == 38);
       }
       catch(Exception e)
       {
           e.printStackTrace();
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
            DriverInfo driverInfo = driversInfo.get(0);
            Assert.assertTrue(driverInfo.getCompletedLaps() == 4);
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
            DriverInfo driverInfo = driversInfo.get(0);
            Date raceTime = new Timestamp(lapTimeDateFormat.parse("04:11.578").getTime());
            Date totalRaceTime = new Timestamp(driverInfo.getTotalRaceTime().getTime());
            Assert.assertTrue(driverInfo.getTotalRaceTime().getTime() == raceTime.getTime());
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
            DriverInfo driverInfo = driversInfo.get(0);
            Date lapTime = new Timestamp(lapTimeDateFormat.parse("01:02.769").getTime());
            Assert.assertTrue(driverInfo.getBestLap().getTime() == lapTime.getTime());
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
            Date lapTime = new Timestamp(lapTimeDateFormat.parse("01:02.769").getTime());

            for(DriverInfo driverInfo : driversInfo)
            {
                if(driverInfo.isRaceBestLap())
                {
                    Assert.assertTrue(driverInfo.getBestLap().getTime() == lapTime.getTime());
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
            DriverInfo driverInfo = driversInfo.get(1);
            Date gapToLeader = new Timestamp(lapTimeDateFormat.parse("00:05.117").getTime());
            Assert.assertTrue(driverInfo.getGapToLeader().getTime() == gapToLeader.getTime());
        }
        catch(Exception e)
        {
            e.printStackTrace();
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
            DriverInfo driverInfo = driversInfo.get(0);
            Assert.assertTrue(String.format("%.3f", driverInfo.getTotalSpeed() / driverInfo.getCompletedLaps()).equals("44.246"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
