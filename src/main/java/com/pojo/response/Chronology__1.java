
package com.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chronology__1 {

    @SerializedName("calendarType")
    @Expose
    private String calendarType;
    @SerializedName("id")
    @Expose
    private String id;

    public String getCalendarType() {
        return calendarType;
    }

    public void setCalendarType(String calendarType) {
        this.calendarType = calendarType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
