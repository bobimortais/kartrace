package com.test.kartrace.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileProcessor
{
    public static List<String> getLinesFromFile(String filePath)
    {
        List<String> fileLines = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(Paths.get(filePath));)
        {
            fileLines = br.lines().collect(Collectors.toList());
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileLines;
    }
}
