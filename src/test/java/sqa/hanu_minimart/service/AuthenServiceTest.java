package sqa.hanu_minimart.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sqa.hanu_minimart.model.Role;
import sqa.hanu_minimart.model.RoleName;
import sqa.hanu_minimart.model.User;
import sqa.hanu_minimart.payload.ApiResponse;
import sqa.hanu_minimart.payload.LoginRequest;
import sqa.hanu_minimart.payload.SignUpRequest;
import sqa.hanu_minimart.repository.RoleRepository;
import sqa.hanu_minimart.repository.UserRepository;
import sqa.hanu_minimart.security.JwtTokenProvider;

@ContextConfiguration(classes = {AuthenService.class, JwtTokenProvider.class, CartService.class})
@ExtendWith(SpringExtension.class)
public class AuthenServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenService authenService;

    @MockBean
    private CartService cartService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User("Name", "Username", "0963062140", "Address", "Password", "ACTIVATED");

        when(userRepository.findByUsernameOrPhoneNumber("foo","foo")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    public void testAuthenticateUser() {
        LoginRequest loginRequest = mock(LoginRequest.class);
        when(loginRequest.getPassword()).thenReturn("foo");
        when(loginRequest.getUsernameOrEmail()).thenReturn("foo");
        assertThat(this.authenService.authenticateUser(loginRequest).getStatusCode().toString()).isEqualTo("200 OK");
    }

    @Test
    public void testRegisterUser() {
        when(this.userRepository.existsByUsername(anyString())).thenReturn(true);
        ResponseEntity<?> actualRegisterUserResult = this.authenService
                .registerUser(new SignUpRequest("Name", "janedoe", "4105551212", "42 Main St", "iloveyou"));
        assertTrue(actualRegisterUserResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualRegisterUserResult.getStatusCode());
        assertEquals("Username is already taken!", ((ApiResponse) actualRegisterUserResult.getBody()).getMessage());
        assertFalse(((ApiResponse) actualRegisterUserResult.getBody()).getSuccess());
        verify(this.userRepository).existsByUsername(anyString());
    }

    @Test
    public void testRegisterUser2() {
        when(this.userRepository.existsByPhoneNumber(anyString())).thenReturn(true);
        when(this.userRepository.existsByUsername(anyString())).thenReturn(false);
        ResponseEntity<?> actualRegisterUserResult = this.authenService
                .registerUser(new SignUpRequest("Name", "janedoe", "4105551212", "42 Main St", "iloveyou"));
        assertTrue(actualRegisterUserResult.hasBody());
        assertEquals(HttpStatus.BAD_REQUEST, actualRegisterUserResult.getStatusCode());
        assertEquals("Phone Number Address already in use!",
                ((ApiResponse) actualRegisterUserResult.getBody()).getMessage());
        assertFalse(((ApiResponse) actualRegisterUserResult.getBody()).getSuccess());
        verify(this.userRepository).existsByPhoneNumber(anyString());
        verify(this.userRepository).existsByUsername(anyString());
    }

    @Test
    public void testRegisterUser3() {
        when(this.userRepository.existsByPhoneNumber(anyString())).thenReturn(false);
        when(this.userRepository.existsByUsername(anyString())).thenReturn(false);
        Role role = new Role(1L,RoleName.ROLE_CUSTOMER);
        when(this.roleRepository.findByName((sqa.hanu_minimart.model.RoleName) any())).thenReturn(Optional.of(role));
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("foo");
        assertThat(this.authenService.registerUser(new SignUpRequest("Name", "janedoe", "4105551212", "42 Main St", "iloveyou"))
                .getStatusCode().toString()).isEqualTo("201 CREATED");
        verify(this.passwordEncoder).encode((CharSequence) any());
        verify(this.roleRepository).findByName((sqa.hanu_minimart.model.RoleName) any());
        verify(this.userRepository).existsByPhoneNumber(anyString());
        verify(this.userRepository).existsByUsername(anyString());
    }
}

