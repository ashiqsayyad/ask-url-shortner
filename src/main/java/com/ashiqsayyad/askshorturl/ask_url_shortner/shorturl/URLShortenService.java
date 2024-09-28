package com.ashiqsayyad.askshorturl.ask_url_shortner.shorturl;

import java.security.*;

import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class URLShortenService {
	
	private static final int SHORT_URL_LENGTH = 8; // Desired length of the encoded short URL
	private static final String BASE_URL = "http://localhost:8083/";
	
	public String generateShortUrl(String longUrl) {
        try {
            // Hash the long URL
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(longUrl.getBytes());  //256bits÷8bits/byte=32bytes is the size of the hashBytes array

            // Encode the hash bytes
            String base64Encoded = Base64.getUrlEncoder().encodeToString(hashBytes);
            
            // Use a substring of the base64 encoded string to create a short URL >> 
            //64 power 8  =281,474,976,710,656 ~=281.5 trillion combinations: This number represents the total possible unique 8-character strings that can be generated using a set of 64 different characters.
            return base64Encoded.substring(0, SHORT_URL_LENGTH);  
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found", e);
        }
    }

}
