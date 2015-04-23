package com.me.moviebooking.model;

import java.io.Serializable;

public class Movie implements Serializable{
	int movieId;
	String name;
	String cast;
	int rating;
	String imdb;
	String rottenTomatoes;
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Movie(int movieId, String name, String cast, int rating,
			String imdb, String rottenTomatoes) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.cast = cast;
		this.rating = rating;
		this.imdb = imdb;
		this.rottenTomatoes = rottenTomatoes;
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCast() {
		return cast;
	}
	public void setCast(String cast) {
		this.cast = cast;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getImdb() {
		return imdb;
	}
	public void setImdb(String imdb) {
		this.imdb = imdb;
	}
	public String getRottenTomatoes() {
		return rottenTomatoes;
	}
	public void setRottenTomatoes(String rottenTomatoes) {
		this.rottenTomatoes = rottenTomatoes;
	}	
	public String toString(){
		return this.getName();
	}
}
