package com.kamlesh.major.model;

import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class customUserDetail extends User implements UserDetails {
    public customUserDetail(User user){
        super(user);
    }
    @Override
    public List<Role> getRoles() {
        return super.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList=new ArrayList<>();
        super.getRoles().forEach(role->{
            authorityList.add(new SimpleGrantedAuthority(role.annotationType().getName()))
        });
        return null;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
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
