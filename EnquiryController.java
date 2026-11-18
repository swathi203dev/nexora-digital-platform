package com.nexora.platform;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryRepository repository;

    public EnquiryController(EnquiryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody EnquiryRequest request) {

        Enquiry saved = repository.save(
            new Enquiry(
                request.name(),
                request.email(),
                request.message()
            )
        );

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(Map.of(
                "id", saved.getId(),
                "message", "Enquiry received"
            ));
    }

    @GetMapping
    public List<Enquiry> all() {
        return repository.findAll();
    }

    @GetMapping("/health")
    public Map<String, String> health() {

        return Map.of("status", "UP");

    }
}