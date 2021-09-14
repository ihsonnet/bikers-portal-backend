package com.app.absworldxpress.jwt.dto.response;


import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoggedUserDetailsResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String phoneNo;
//    private List<Project> projects;
    private List<String> userRole;
    private Boolean isAuthenticated;
//    private Team teamDetails;
}