package com.ashiqsayyad.askshorturl.ask_url_shortner.shorturl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController

public class URLShortenController {
	
	@Autowired
	private URLShortenService urlShortenService;
	
	
	@GetMapping("/{short_url}")
	public RedirectView shortenURL(@PathVariable String short_url ) {
		System.out.println("Short URL code is :"+urlShortenService.generateShortUrl("www.google.com"));
		 return new RedirectView("https://www.google.com"); 
	}
	
	@PostMapping("/api/shorturl")
	public ResponseEntity<String> navigate() {
		return new ResponseEntity<String> ("shor coce",HttpStatus.CREATED);
		
	}

}
