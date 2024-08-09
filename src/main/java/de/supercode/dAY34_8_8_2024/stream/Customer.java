package de.supercode.dAY34_8_8_2024.stream;

import java.time.Instant;

public class Customer {
    private String firstname;
    private String lastname;
    private String email;
    private Instant customerSince;

    public Customer(String firstname, String lastname, String email, Instant customerSince) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setCustomerSince(customerSince);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        if (firstname == null || firstname.trim().length() == 0)
            throw new IllegalArgumentException("Firstname must be set");
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        if (lastname == null || lastname.trim().length() == 0)
            throw new IllegalArgumentException("Lastname must be set");
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().length() == 0) throw new IllegalArgumentException("Email must be set");
        this.email = email;
    }

    public Instant getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(Instant customerSince) {
        if (customerSince == null) throw new IllegalArgumentException("CustomerSince must be set");
        this.customerSince = customerSince;
    }
}