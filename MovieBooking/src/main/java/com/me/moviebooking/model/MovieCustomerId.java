package com.me.moviebooking.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MovieCustomerId implements Serializable {
    protected Integer movieId;
    protected Integer customerId;

    public MovieCustomerId() {}

    public MovieCustomerId(Integer movieId, Integer customerId) {
        this.movieId = movieId;
        this.customerId = customerId;
    }
}