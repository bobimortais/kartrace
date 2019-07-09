package com.test.kartrace.display;

import com.test.kartrace.entity.DriverInfo;
import java.text.SimpleDateFormat;
import java.util.List;

public class DisplayRaceResults
{
    /**
     * Method to show the race results
     * @param driversResults - List with the results for each driver
     */
    public static void showRaceResults(List<DriverInfo> driversResults)
    {
        String leftAlignFormat = "| %-7s | %-6s | %-14s | %-6s | %-12s | %-16s | %-16s | %-11s |%n";
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss:SSS");

        System.out.format("+---------+--------+----------------+--------+--------------+------------------+------------------+-------------+%n");
        System.out.format("| Posicao | Codigo | Piloto         | Voltas | Melhor Volta | Velocidade Media | Tempo para lider | Tempo Total |%n");
        System.out.format("+---------+--------+----------------+--------+--------------+------------------+------------------+-------------+%n");

        for(DriverInfo driver : driversResults)
        {
            System.out.format(leftAlignFormat,
                    driver.getDriverPosition(),
                    driver.getDriverId(),
                    driver.getDriverName(),
                    driver.getCompletedLaps(),
                    driver.isRaceBestLap() ? dateFormat.format(driver.getBestLap()) + "*" : dateFormat.format(driver.getBestLap()),
                    String.format("%.3f", driver.getAvgSpeed() / driver.getCompletedLaps()),
                    dateFormat.format(driver.getGapToLeader()),
                    dateFormat.format(driver.getTotalRaceTime()));
        }
        System.out.format("+---------+--------+----------------+--------+--------------+------------------+------------------+-------------+%n");
        System.out.println("*Melhor volta da corrida");
    }
}
