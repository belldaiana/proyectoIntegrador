package com.example.BackPI.controller;

import com.example.BackPI.Dto.JwtDTO;
import com.example.BackPI.Dto.UsuarioDTO;
import com.example.BackPI.jwt.resource.JwtProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProviderConfig jwtProviderConfig;

    /*Generación del Token*/
    @PostMapping("/token")
    public ResponseEntity<Map<String, Object>> token(@RequestBody UsuarioDTO usuario) {
        Map<String, Object> response = new HashMap<>();

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProviderConfig.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, "Bearer", userDetails.getUsername(), userDetails.getAuthorities());
        response.put("respuesta", jwtDTO);
        return ResponseEntity.ok(response);
    }
}
