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
}
