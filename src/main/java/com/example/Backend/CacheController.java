package com.example.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @PostMapping
    public ResponseEntity<String> store(@RequestBody KeyValue pair) {
        String result = cacheService.put(pair.getKey(), pair.getValue());
         if (result != null && result.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Value stored");
    }

    @GetMapping("/{key}")
    public ResponseEntity<String> retrieve(@PathVariable String key) {
        String value = cacheService.get(key);
        if (value == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: Key not found");
        }
        return ResponseEntity.ok(value);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> remove(@PathVariable String key) {
       // cacheService.delete(key);
       // return ResponseEntity.ok().build();
     Boolean   removed =  cacheService.delete(key);
     if (removed) {
         return ResponseEntity.ok("Key removed successfully."); // 200 OK with message
     } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND)
                 .body("Error: Key not found"); // 404 Not Found with message
     }
        
    }

    // Inner class to represent the key-value pair in the request body
    static class KeyValue {
        private String key;
        private String value;

        // Getters and setters
        public String getKey() { return key; }
        public void setKey(String key) { this.key = key; }
        public String getValue() { return value; }
        public void setValue(String value) { this.value = value; }
    }
}
