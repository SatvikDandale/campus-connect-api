package com.campussocialmedia.campussocialmedia.entity;

public class EventAdd {
    private String committeeUserName;
    private String title;
    private String description;
    private String startDate;
    private String endDate;

    public EventAdd() {
    }

    public EventAdd(String committeeUserName, String title, String description, String startDate, String endDate) {
        this.committeeUserName = committeeUserName;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCommitteeUserName() {
        return committeeUserName;
    }

    public void setCommitteeUserName(String committeeUserName) {
        this.committeeUserName = committeeUserName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "EventAdd [committeeUserName=" + committeeUserName + ", description=" + description + ", endDate="
                + endDate + ", startDate=" + startDate + ", title=" + title + "]";
    }

    
    
}
