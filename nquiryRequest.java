package com.nexora.platform;

import jakarta.validation.constraints.*;

public record EnquiryRequest(

    @NotBlank
    @Size(max = 100)
    String name,

    @NotBlank
    @Email
    @Size(max = 150)
    String email,

    @NotBlank
    @Size(max = 2000)
    String message

) {}