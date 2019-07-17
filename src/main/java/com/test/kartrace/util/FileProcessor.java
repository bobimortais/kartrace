package com.test.kartrace.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to read the input file from the file system
 */
public class FileProcessor
{
    private FileProcessor()
    {

    }

    public static List<String> getLinesFromFile(String filePath) throws IOException
    {
        List<String> fileLines;
        try(BufferedReader br = Files.newBufferedReader(Paths.get(filePath));)
        {
            fileLines = br.lines().collect(Collectors.toList());
        }
        return fileLines;
    }
}
