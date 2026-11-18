package com.nexora.platform;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 2000)
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();

    protected Enquiry() {}

    public Enquiry(String name, String email, String message) {

        this.name = name;
        this.email = email;
        this.message = message;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}