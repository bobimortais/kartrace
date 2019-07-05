package com.test.kartrace.entity;

import java.util.Date;

/**
 * Class to represent the race results for each driver
 */
public class DriverInfo
{
    private long driverId;

    private String driverName;

    private int driverPosition;

    private int completedLaps;

    private Date totalRaceTime;

    private int bestLap;

    private boolean raceBestLap;

    private double avgSpeed;

    private double gapToLeader;

    public long getDriverId()
    {
        return driverId;
    }

    public void setDriverId(long driverId)
    {
        this.driverId = driverId;
    }

    public String getDriverName()
    {
        return driverName;
    }

    public void setDriverName(String driverName)
    {
        this.driverName = driverName;
    }

    public int getDriverPosition()
    {
        return driverPosition;
    }

    public void setDriverPosition(int driverPosition)
    {
        this.driverPosition = driverPosition;
    }

    public int getCompletedLaps()
    {
        return completedLaps;
    }

    public void setCompletedLaps(int completedLaps)
    {
        this.completedLaps = completedLaps;
    }

    public Date getTotalRaceTime()
    {
        return totalRaceTime;
    }

    public void setTotalRaceTime(Date totalRaceTime)
    {
        this.totalRaceTime = totalRaceTime;
    }

    public int getBestLap()
    {
        return bestLap;
    }

    public void setBestLap(int bestLap)
    {
        this.bestLap = bestLap;
    }

    public boolean isRaceBestLap()
    {
        return raceBestLap;
    }

    public void setRaceBestLap(boolean raceBestLap)
    {
        this.raceBestLap = raceBestLap;
    }

    public double getAvgSpeed()
    {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed)
    {
        this.avgSpeed = avgSpeed;
    }

    public double getGapToLeader()
    {
        return gapToLeader;
    }

    public void setGapToLeader(double gapToLeader)
    {
        this.gapToLeader = gapToLeader;
    }
}
