package com.test.kartrace.entity;

import java.time.LocalTime;
import java.util.Date;

public class LapInfo
{
    private LocalTime lapTimeStamp;

    private long driverId;

    private String driverName;

    private int lapNumber;

    private Date lapTime;

    private double avgLapSpeed;

    public LocalTime getLapTimeStamp()
    {
        return lapTimeStamp;
    }

    public void setLapTimeStamp(LocalTime lapTimeStamp)
    {
        this.lapTimeStamp = lapTimeStamp;
    }

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

    public int getLapNumber()
    {
        return lapNumber;
    }

    public void setLapNumber(int lapNumber)
    {
        this.lapNumber = lapNumber;
    }

    public Date getLapTime()
    {
        return lapTime;
    }

    public void setLapTime(Date lapTime)
    {
        this.lapTime = lapTime;
    }

    public double getAvgLapSpeed()
    {
        return avgLapSpeed;
    }

    public void setAvgLapSpeed(double avgLapSpeed)
    {
        this.avgLapSpeed = avgLapSpeed;
    }
}
