package sqa.hanu_minimart.controller;

import sqa.hanu_minimart.model.*;
import sqa.hanu_minimart.payload.ApiResponse;
import sqa.hanu_minimart.payload.JwtAuthenticationResponse;
import sqa.hanu_minimart.payload.LoginRequest;
import sqa.hanu_minimart.payload.SignUpRequest;
import sqa.hanu_minimart.repository.RoleRepository;
import sqa.hanu_minimart.repository.UserRepository;
import sqa.hanu_minimart.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sqa.hanu_minimart.service.AuthenService;
import sun.applet.AppletSecurityException;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenService authenService;

    @PostMapping(path="/signin", consumes="application/json")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authenService.authenticateUser(loginRequest);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authenService.registerUser(signUpRequest);
    }
}
