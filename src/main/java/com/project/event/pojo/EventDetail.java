package com.project.event.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kausha on 2/2/16.
 */
public class EventDetail {

    @SerializedName("event_id")
    private String id;

    @SerializedName("event_name")
    private String name;

    @SerializedName("event_image")
    private String imageUrl;

    @SerializedName("event_venue")
    private String venue;

    @SerializedName("event_schedule_start")
    private String start;

    @SerializedName("event_schedule_end")
    private String end;

    @SerializedName("event_cost")
    private String cost;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
