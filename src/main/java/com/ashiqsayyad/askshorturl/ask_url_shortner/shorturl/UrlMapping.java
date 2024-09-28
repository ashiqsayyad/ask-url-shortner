package com.ashiqsayyad.askshorturl.ask_url_shortner.shorturl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UrlMapping {

    @Override
	public String toString() {
		return "UrlMapping [shortUrl=" + shortUrl + ", longUrl=" + longUrl + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing ID
    private Long id;
    
    @Column(name = "shorturl",nullable = false, unique = true)
    private String shortUrl;
    
    @Column(name = "longurl",nullable = false)
    private String longUrl;

    // Getters and Setters
}
