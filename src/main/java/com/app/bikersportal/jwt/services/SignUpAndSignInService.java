package com.app.bikersportal.jwt.services;

import com.app.bikersportal.jwt.dto.request.SignUpForm;
import com.app.bikersportal.jwt.dto.response.JwtResponse;
import com.app.bikersportal.jwt.dto.response.LoggedUserDetailsResponse;
import com.app.bikersportal.jwt.dto.response.UserResponse;
import com.app.bikersportal.jwt.repository.RoleRepository;
import com.app.bikersportal.jwt.repository.UserRepository;
import com.app.bikersportal.jwt.security.jwt.JwtProvider;
import com.app.bikersportal.jwt.dto.request.LoginForm;
import com.app.bikersportal.jwt.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@AllArgsConstructor
@Service
public class SignUpAndSignInService {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    AuthService authService;

    public Object signup(SignUpForm signUpRequest) {


        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            //return true;
            return new JwtResponse("Email Already Exists");
        }


        User user = new User();
        UUID id = UUID.randomUUID();
        String uuid = id.toString();
        user.setId(uuid);
        user.setFullName(signUpRequest.getFullName());

        String[] arrOfUsername = signUpRequest.getEmail().split("@", 2);
        user.setUsername(arrOfUsername[0]);

        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(authService.getRolesFromStringToRole(signUpRequest.getRole()));

        user.setCreatedBy(arrOfUsername[0]);
//        user.setCreatedOn(signUpRequest.getCreatedOn());

        userRepository.saveAndFlush(user);
        System.out.println(1);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        signUpRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        return new JwtResponse("OK", jwt, signUpRequest.getRole());
    }


    public JwtResponse signIn(LoginForm loginRequest) {

        Optional<User> userOptional;
        if (loginRequest.getUsername().contains("@")){
                userOptional = userRepository.findByEmail(loginRequest.getUsername());
        }
        else {
                userOptional = userRepository.findByUsername(loginRequest.getUsername());
        }

        String userName;

        if (userOptional.isPresent()) {
            userName = userOptional.get().getUsername();
        } else {
            userName = "";
            //throw new ResponseStatusException(HttpStatus.valueOf(410),"User Not Exists");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userName,
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        System.out.println(jwt);
        return new JwtResponse("OK", jwt, authService.getRolesStringFromRole(userOptional.get().getRoles()));
    }

    public LoggedUserDetailsResponse getLoggedUserDetails(Authentication authentication) {

        System.out.println(authentication.toString());
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        List<String> userRoleList = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            userRoleList.add(grantedAuthority.getAuthority());
        }
        LoggedUserDetailsResponse loggedUserDetailsResponse = new LoggedUserDetailsResponse();
        loggedUserDetailsResponse.setUserRole(userRoleList);
        loggedUserDetailsResponse.setIsAuthenticated(authentication.isAuthenticated());

        Optional<User> user = userRepository.findByUsername(authentication.getName());
//        Team team = teamRepository.findByCreatedBy(user.get().getId()).get();

        loggedUserDetailsResponse.setId(user.get().getId());
        loggedUserDetailsResponse.setFullName(user.get().getFullName());
        loggedUserDetailsResponse.setUsername(user.get().getUsername());
        loggedUserDetailsResponse.setEmail(user.get().getEmail());
        loggedUserDetailsResponse.setPhoneNo(user.get().getPhoneNo());
//        loggedUserDetailsResponse.setProjects(user.get().getProjects());
//        loggedUserDetailsResponse.setTeamDetails(team);
        return loggedUserDetailsResponse;
    }

}
