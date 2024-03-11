package com.glab.minihackathon3_rest_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.apache.tomcat.util.codec.binary.Base64;

@Entity
public class Movie {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
   // private long id;
    @Id
    private String title ;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

   @JsonProperty("Title")
   public String getTitle() {
       return this.title;
   }
    @JsonProperty("Rated")
    public String getRated() {
        return this.rated;
    }
    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }
    @JsonProperty("Rated")
    public void setRated(String rated) {
        this.rated = rated;
    }
    @JsonProperty("Released")
    public String getReleased() {
        return released;
    }
    @JsonProperty("Released")
    public void setReleased(String released) {
        this.released = released;
    }
    @JsonProperty("Runtime")
    public String getRuntime() {
        return runtime;
    }
    @JsonProperty("Runtime")
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }
    @JsonProperty("Genre")
    public String getGenre() {
        return genre;
    }
    @JsonProperty("Genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }
    @JsonProperty("Director")
    public String getDirector() {
        return director;
    }
    @JsonProperty("Director")
    public void setDirector(String director) {
        this.director = director;
    }
    @JsonProperty("Actors")
    public String getActors() {
        return actors;
    }
    @JsonProperty("Actors")
    public void setActors(String actors) {
        this.actors = actors;
    }
    @JsonProperty("Plot")
    public String getPlot() {
        return plot;
    }
    @JsonProperty("Plot")
    public void setPlot(String plot) {
        this.plot = plot;
    }
    @JsonProperty("Language")
    public String getLanguage() {
        return language;
    }
    @JsonProperty("Language")
    public void setLanguage(String language) {
        this.language = language;
    }
    @JsonProperty("Country")
    public String getCountry() {
        return country;
    }
    @JsonProperty("Country")
    public void setCountry(String country) {
        this.country = country;
    }
    @JsonProperty("Awards")
    public String getAwards() {
        return awards;
    }
    @JsonProperty("Awards")
    public void setAwards(String awards) {
        this.awards = awards;
    }
    @JsonProperty("Poster")
    public String getPoster() {
        return poster;
    }
    @JsonProperty("Poster")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    public byte[] getImageData() {

        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    public String generateBase64Image() {
        return Base64.encodeBase64String(this.imageData);
    }
}
