package com.petshop1018.sungil.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
public class CheckoutForm implements Serializable {
    @Valid
    private Address address;
    private String paymentMethod;
    private int total;

    @Data
    public static class Address {
        @NotBlank(message = "First Name is required")
        private String firstName;

        @NotBlank(message = "Last Name is required")
        private String lastName;

        private String companyName; // Optional

        @NotBlank(message = "Country is required")
        private String country;

        @NotBlank(message = "Street Address is required")
        private String address1;

        private String address2; // Optional

        @NotBlank(message = "City is required")
        private String city;

        @NotBlank(message = "State is required")
        private String state;

        @NotBlank(message = "Zip Code is required")
        @Pattern(regexp = "\\d{5}", message = "Invalid Zip Code")
        private String zip;

        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number")
        private String phone;

        private String orderEmail;

        // Additional Information
        private String orderNotes;
    }

}
