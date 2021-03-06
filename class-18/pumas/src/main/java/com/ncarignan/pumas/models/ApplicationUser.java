package com.ncarignan.pumas.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    // matches the property on the other class
    @OneToMany(mappedBy = "applicationUser")
    List<Puma> pumas;

    public List<Puma> getPumas(){
        return this.pumas;
    }

    String username;
    String password;

    public ApplicationUser() {};

    public ApplicationUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean allMyPumasAreFighting(){
        // check if all pumas are fighting
        // if every single puma
        // was attacking every single other puma
        for(Puma puma : pumas){
            // check am I hunting everyone
            for(Puma prey : pumas){
                if(puma.equals(prey)) {
                    continue;
                }
                if(!puma.pumasIAmHunting.contains(prey)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
