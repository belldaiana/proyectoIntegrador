package com.example.BackPI.Dto;

import com.example.BackPI.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UsuarioDTO {

    private Integer id;

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String ciudad;
    private RolDTO rol;

}
