package com.ashiqsayyad.askshorturl.ask_url_shortner.shorturl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    
}
