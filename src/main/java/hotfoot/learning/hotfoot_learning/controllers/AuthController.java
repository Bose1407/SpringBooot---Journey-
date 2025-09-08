package hotfoot.learning.hotfoot_learning.controllers;

import hotfoot.learning.hotfoot_learning.entity.UserEntity;
import hotfoot.learning.hotfoot_learning.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserEntity user;
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> Login(@RequestBody UserEntity user){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails);
            return new ResponseEntity<>(Map.of("token",token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("Error","Invalid Token"),HttpStatus.UNAUTHORIZED);
        }
    }
}
