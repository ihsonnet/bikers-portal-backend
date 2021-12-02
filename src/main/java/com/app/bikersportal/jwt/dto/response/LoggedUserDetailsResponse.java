package com.app.bikersportal.jwt.dto.response;


import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoggedUserDetailsResponse {

    private String id;
    private String fullName;
    private String email;
    private String username;
    private String phoneNo;
//    private List<Project> projects;
    private List<String> userRole;
    private Boolean isAuthenticated;
//    private Team teamDetails;
}