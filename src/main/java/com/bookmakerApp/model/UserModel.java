package com.bookmakerApp.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.NotImplementedException;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.util.Collection;

@Getter
@Setter
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idUser;
    private String password;
    private String name;
    private String surname;
    @Min(18)
    private int age;
    @Email
    private String mail;
    @OneToOne
    @JoinColumn(name = "idAccount")
    private AccountModel account;

    //TODO : Class need to implement UserDetails
    public Collection<Integer> getAuthorities(){
        throw new NotImplementedException("Not implemented yet");
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}

