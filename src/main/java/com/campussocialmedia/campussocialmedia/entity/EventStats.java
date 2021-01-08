package com.campussocialmedia.campussocialmedia.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;

@DynamoDBDocument
public class EventStats {
    private String noOfParticipants; //1k+ participants
    private String noOfEvents; //100+ events
    private String cashPrize; //40k+ cash prize

    public EventStats() {
    }

    public EventStats(String noOfParticipants, String noOfEvents, String cashPrize) {
        this.noOfParticipants = noOfParticipants;
        this.noOfEvents = noOfEvents;
        this.cashPrize = cashPrize;
    }

    public String getNoOfParticipants() {
        return noOfParticipants;
    }

    public void setNoOfParticipants(String noOfParticipants) {
        this.noOfParticipants = noOfParticipants;
    }

    public String getNoOfEvents() {
        return noOfEvents;
    }

    public void setNoOfEvents(String noOfEvents) {
        this.noOfEvents = noOfEvents;
    }

    public String getCashPrize() {
        return cashPrize;
    }

    public void setCashPrize(String cashPrize) {
        this.cashPrize = cashPrize;
    }

    @Override
    public String toString() {
        return "EventStats [cashPrize=" + cashPrize + ", noOfEvents=" + noOfEvents + ", noOfParticipants="
                + noOfParticipants + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cashPrize == null) ? 0 : cashPrize.hashCode());
        result = prime * result + ((noOfEvents == null) ? 0 : noOfEvents.hashCode());
        result = prime * result + ((noOfParticipants == null) ? 0 : noOfParticipants.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventStats other = (EventStats) obj;
        if (cashPrize == null) {
            if (other.cashPrize != null)
                return false;
        } else if (!cashPrize.equals(other.cashPrize))
            return false;
        if (noOfEvents == null) {
            if (other.noOfEvents != null)
                return false;
        } else if (!noOfEvents.equals(other.noOfEvents))
            return false;
        if (noOfParticipants == null) {
            if (other.noOfParticipants != null)
                return false;
        } else if (!noOfParticipants.equals(other.noOfParticipants))
            return false;
        return true;
    }

    
}
