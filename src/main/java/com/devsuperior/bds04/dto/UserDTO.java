package com.devsuperior.bds04.dto;

import com.devsuperior.bds04.entities.User;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;

    private String firstName;
    
    private String email;
    
    
    public UserDTO(Long id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;

    }

    public UserDTO() {

    }

    public UserDTO(User x) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
