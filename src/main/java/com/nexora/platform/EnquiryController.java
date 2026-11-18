package com.nexora.platform;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enquiries")
@CrossOrigin
public class EnquiryController {

    private final EnquiryRepository repository;

    public EnquiryController(EnquiryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Enquiry create(@RequestBody Enquiry enquiry) {
        return repository.save(enquiry);
    }

    @GetMapping
    public List<Enquiry> getAll() {
        return repository.findAll();
    }
}
