package com.test.kartrace.display;

import com.test.kartrace.entity.DriverInfo;
import java.text.SimpleDateFormat;
import java.util.List;

public class DisplayRaceResults
{
    private DisplayRaceResults()
    {

    }

    public static final String SEPARATOR = "+---------+--------+----------------+--------+--------------+------------------+------------------+-------------+%n";

    public static final String FIELDS_ALIGNMENT = "| %-7s | %-6s | %-14s | %-6s | %-12s | %-16s | %-16s | %-11s |%n";

    public static final String FIELDS = "| Posicao | Codigo | Piloto         | Voltas | Melhor Volta | Velocidade Media | Tempo para lider | Tempo Total |%n";
    /**
     * Method to show the race results
     * @param driversResults - List with the results for each driver
     */
    public static void showRaceResults(List<DriverInfo> driversResults)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss:SSS");

        System.out.format(SEPARATOR);
        System.out.format(FIELDS);
        System.out.format(SEPARATOR);

        for(DriverInfo driver : driversResults)
        {
            System.out.format(FIELDS_ALIGNMENT,
                    driver.getDriverPosition(),
                    driver.getDriverId(),
                    driver.getDriverName(),
                    driver.getCompletedLaps(),
                    driver.isRaceBestLap() ? dateFormat.format(driver.getBestLap()) + "*" : dateFormat.format(driver.getBestLap()),
                    formatAvgSpeed(driver.getTotalSpeed(), driver.getCompletedLaps()),
                    dateFormat.format(driver.getGapToLeader()),
                    dateFormat.format(driver.getTotalRaceTime()));
        }
        System.out.format(SEPARATOR);
        System.out.println("*Melhor volta da corrida");
    }

    /**
     * Method to format driver average speed
     * @param totalSpeed
     * @param completedLaps
     * @return String - average speed
     */
    private static String formatAvgSpeed(double totalSpeed, int completedLaps)
    {
        return String.format("%.3f", totalSpeed / completedLaps);
    }
}
