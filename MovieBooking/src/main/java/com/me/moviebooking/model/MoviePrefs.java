package com.me.moviebooking.model;

import java.util.List;

public class MoviePrefs {
	private Customer customer;
	private List<Showtime> showtimes;
	private UserPreferences up;
	
	public MoviePrefs(){
		super();
	}
	
	public MoviePrefs(Customer customer, List<Showtime> showtimes,
			UserPreferences up) {
		super();
		this.customer = customer;
		this.showtimes = showtimes;
		this.up = up;
	}	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public UserPreferences getUp() {
		return up;
	}
	public void setUp(UserPreferences up) {
		this.up = up;
	}
	
	public List<Showtime> getShowtimes() {
		return showtimes;
	}

	public void setShowtimes(List<Showtime> showtimes) {
		this.showtimes = showtimes;
	}
}
