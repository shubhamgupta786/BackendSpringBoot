package com.example.Backend;

import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CacheService {

    private final int maxSize = 10; // Define the maximum size
    private final Map<String, String> cache = new LinkedHashMap<String, String>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
            return size() > maxSize;
        }
    };

    public String get(String key) {
        return cache.get(key);
    }

    public String put(String key, String value) {
        if (cache.size() >= maxSize && !cache.containsKey(key)) {
            return "Error: Cache is full";
        }
        return cache.put(key, value);
    }

    public boolean  delete(String key) {
    	if (cache.containsKey(key)) {
            cache.remove(key);
            return true; // Key was removed
        } else {
            return false; // Key didn't exist
        }
    }
    
    public void printCache() {
        System.out.println("Current Cache Contents:");
        for (Map.Entry<String, String> entry : cache.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
