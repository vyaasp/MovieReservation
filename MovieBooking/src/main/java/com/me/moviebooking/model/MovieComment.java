package com.me.moviebooking.model;

import java.io.Serializable;

public class MovieComment implements Serializable{
	
	Movie movie;
	Customer customer;
	String comment;
	
	public MovieComment(Movie movie, Customer customer, String comment) {
		super();
		this.movie = movie;
		this.customer = customer;
		this.comment = comment;
	}	
	public MovieComment() {
		super();
	}
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
