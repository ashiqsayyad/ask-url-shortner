package com.ashiqsayyad.askshorturl.ask_url_shortner.shorturl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class URLShortenController {
	
	
	@GetMapping("/hello")
	public RedirectView shortenURL() {
		 return new RedirectView("https://www.google.com"); 
	}

}
