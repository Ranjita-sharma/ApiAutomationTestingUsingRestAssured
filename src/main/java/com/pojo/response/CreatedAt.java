
package com.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedAt {

    @SerializedName("dayOfWeek")
    @Expose
    private String dayOfWeek;
    @SerializedName("month")
    @Expose
    private String month;
    @SerializedName("hour")
    @Expose
    private Integer hour;
    @SerializedName("dayOfYear")
    @Expose
    private Integer dayOfYear;
    @SerializedName("dayOfMonth")
    @Expose
    private Integer dayOfMonth;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("monthValue")
    @Expose
    private Integer monthValue;
    @SerializedName("nano")
    @Expose
    private Integer nano;
    @SerializedName("chronology")
    @Expose
    private Chronology__1 chronology;
    @SerializedName("minute")
    @Expose
    private Integer minute;
    @SerializedName("second")
    @Expose
    private Integer second;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(Integer dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public Integer getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(Integer dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonthValue() {
        return monthValue;
    }

    public void setMonthValue(Integer monthValue) {
        this.monthValue = monthValue;
    }

    public Integer getNano() {
        return nano;
    }

    public void setNano(Integer nano) {
        this.nano = nano;
    }

    public Chronology__1 getChronology() {
        return chronology;
    }

    public void setChronology(Chronology__1 chronology) {
        this.chronology = chronology;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

}
