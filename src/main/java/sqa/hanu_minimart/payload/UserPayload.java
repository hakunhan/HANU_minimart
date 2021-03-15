package sqa.hanu_minimart.payload;

import org.hibernate.annotations.NaturalId;
import sqa.hanu_minimart.model.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class UserPayload {
    @NotBlank
    @NotNull
    @Size(max = 30)
    @Column(length = 30)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 30)
    @Column(length = 30)
    private String username;

    @NaturalId
    @NotBlank
    @NotNull
    @Size(max = 30)
    @Column(length = 30)
    private String phoneNumber;

    @Size(max = 100)
    @Column(length = 100)
    @NotNull
    private String address;


    public UserPayload(@NotBlank @NotNull @Size(max = 30) String name, @NotBlank @NotNull @Size(max = 30) String username, @NotBlank @NotNull @Size(max = 30) String phoneNumber, @Size(max = 100) @NotNull String address) {
        this.name = name;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
