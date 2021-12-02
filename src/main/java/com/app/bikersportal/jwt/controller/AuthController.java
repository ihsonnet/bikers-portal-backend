package com.app.bikersportal.jwt.controller;

import com.app.bikersportal.jwt.dto.request.LoginForm;
import com.app.bikersportal.jwt.dto.request.SignUpForm;
import com.app.bikersportal.jwt.dto.response.LoggedUserDetailsResponse;
import com.app.bikersportal.jwt.services.SignUpAndSignInService;
import javassist.bytecode.DuplicateMemberException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private SignUpAndSignInService signUpAndSignInService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        return ResponseEntity.ok(signUpAndSignInService.signIn(loginRequest));
    }

    @PostMapping("/signup")
    public Object ecommerceSignup(@RequestBody SignUpForm signUpRequest) throws DuplicateMemberException {
        return signUpAndSignInService.signup(signUpRequest);
    }

    @GetMapping("/serverCheck")
    public String getServerStatStatus() {
        return "The Server is Running";
    }

    @GetMapping("/user-info")
    public LoggedUserDetailsResponse getUserInfo(Authentication authentication) {

        return signUpAndSignInService.getLoggedUserDetails(authentication);
    }

}
