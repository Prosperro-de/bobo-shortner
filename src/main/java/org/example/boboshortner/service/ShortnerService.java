package org.example.boboshortner.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShortnerService {

    @Value("${base-url}")
    private String baseUrl;

    private Map<String, String> context = new HashMap<>();

    public String createShortUrl(String fullUrl) {

        String key = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        while (context.containsKey(key)) {
            key = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        }
        context.put(key, fullUrl);
        String url = baseUrl + "/" + key;
        return url;
    }

    public String getFullUrl(String shortUrl) {
        URI uri = URI.create(shortUrl);
        String key = uri.getPath().replace("/", "");
        String url = context.get(key);
        return url;
    }
}
