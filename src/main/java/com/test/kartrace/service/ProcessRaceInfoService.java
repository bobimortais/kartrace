package com.test.kartrace.service;

import com.test.kartrace.entity.DriverInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ProcessRaceInfoService
{
    public static void processRaceResults(String filePath)
    {
        try(FileInputStream inputstream = new FileInputStream(filePath);)
        {
            int data = inputstream.read();
            while (data != -1)
            {
                System.out.println( data);
                data = inputstream.read();

            }
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
