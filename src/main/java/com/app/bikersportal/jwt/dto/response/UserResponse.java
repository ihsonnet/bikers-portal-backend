package com.app.bikersportal.jwt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    String id;
    String username;
    String email;
    String fullName;
    String phoneNo;
    Set<String> role;
    String createdBy;
    String createdOn;

}
