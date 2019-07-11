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

    private Date bestLap;

    private boolean raceBestLap;

    private double avgSpeed;

    private Date gapToLeader;

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

    public Date getBestLap()
    {
        return bestLap;
    }

    public void setBestLap(Date bestLap)
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

    public Date getGapToLeader()
    {
        return gapToLeader;
    }

    public void setGapToLeader(Date gapToLeader)
    {
        this.gapToLeader = gapToLeader;
    }

    public void addRaceTime(Date timeToAdd)
    {
        totalRaceTime = new Date(totalRaceTime.getTime() + timeToAdd.getTime());
    }

    public void incrementCompletedLaps()
    {
        completedLaps++;
    }

    public DriverInfo(Long driverId, String driverName)
    {
        this.driverId = driverId;
        this.driverName = driverName;
        totalRaceTime = new Date(0);
        bestLap = new Date(Long.MAX_VALUE);
    }
}
