package sqa.hanu_minimart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sqa.hanu_minimart.model.User;
import sqa.hanu_minimart.payload.LoginRequest;
import sqa.hanu_minimart.payload.SignUpRequest;
import sqa.hanu_minimart.repository.UserRepository;
import sqa.hanu_minimart.service.AccountService;
import sqa.hanu_minimart.service.AuthenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserRepository userRepository;


    //8085/api/account/getAll?id=1
    @Secured("ROLE_CUSTOMER")
    @GetMapping(path="/getAll")
    public ResponseEntity<?> getAllAccount( @RequestParam(required = false) Long id,
                                               @RequestParam(required = false, defaultValue = "_") String name,
                                               @RequestParam(required = false) String username,
                                               @RequestParam(required = false) String phoneNumber,
                                               @RequestParam(required = false, defaultValue = "_") String address,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,

                                               @RequestParam(defaultValue = "id,desc") String[] sort) {

        try {
            return new ResponseEntity<>(accountService.getAllAccount(id, username, phoneNumber, name, address, page, size, sort ), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping(path="/update/{id}")
    public ResponseEntity<?> upadateAccount (@RequestParam Long id, @Valid @RequestBody User request){
        return new ResponseEntity<>(accountService.updateUser(id, request), HttpStatus.OK);
    }

    @GetMapping(path="/delete/{id}")
    public ResponseEntity<?> deleteAccount (@RequestParam Long id){
        accountService.deleteUser(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
