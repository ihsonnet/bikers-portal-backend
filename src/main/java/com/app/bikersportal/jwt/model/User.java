package com.app.bikersportal.jwt.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    String phoneNo;

    @NotBlank
    private String fullName;

    @Column(unique = true)
    @NotBlank
    private String username;

    @Column(unique = true)
    @NaturalId
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @JsonIgnore
    int generatedOTP=1234;

    @JsonIgnore
    boolean passwordReset;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    private String referralUsername;
    private String isActiveIncomeAccount;

    private String createdBy;
    private String createdOn;
}