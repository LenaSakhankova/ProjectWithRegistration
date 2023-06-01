package com.iba.springsecurity.secutity;

import com.iba.springsecurity.entity.PersonEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;


public class PersonDetails implements UserDetails{

   private final PersonEntity personEntity;

    public PersonDetails(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(personEntity.getRole()));
    }

    @Override
    public String getPassword() {
        return this.personEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.personEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public PersonEntity getPersonEntity(){
        return this.personEntity;
    }
}
