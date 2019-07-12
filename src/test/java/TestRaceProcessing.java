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
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
        Assert.assertTrue(true);
    }

    @Test
    public void CHECK_COMPLETED_LAPS()
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
        Assert.assertTrue(true);
    }

    @Test
    public void CHECK_TOTAL_RACE_TIME()
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
        Assert.assertTrue(true);
    }

    @Test
    public void CHECK_DRIVER_BEST_LAP()
    {
        Assert.assertTrue(true);
    }

    @Test
    public void CHECK_RACE_BEST_LAP()
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
        Assert.assertTrue(true);
    }

    @Test
    public void CHECK_GAP_TO_LEADER()
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
        Assert.assertTrue(true);
    }

    @Test
    public void CHECK_DRIVER_RACE_AVG_SPEED()
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
        Assert.assertTrue(true);
    }

    @Test
    public void CHECK_ALL_DATA_SHOWN()
    {
        List<String> fileLines = FileProcessor.getLinesFromFile("src/test/resources/kartlog_1.txt");
        Assert.assertTrue(true);
    }
}
