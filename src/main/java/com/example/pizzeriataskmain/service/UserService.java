package com.example.pizzeriataskmain.service;

import com.example.pizzeriataskmain.dbService.DBService;
import com.example.pizzeriataskmain.dbService.dataSets.Usr;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
//import pizzeria.dbService.DBService;
//import pizzeria.dbService.dataSets.Usr;

@Component
public class UserService implements UserDetailsService {
    private DBService dbService = new DBService();

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usr usr = dbService.getUser(login);
        if(usr == null){
            throw new UsernameNotFoundException(String.format("User with login «%s» not found", login));
        }

        UserDetails user = User.builder()
                .username(usr.getLogin())
                .password(usr.getPassword())
                .roles(usr.getRole())
                .build();
        return user;
    }

    public Usr login(Usr user){
        Usr usr = dbService.getUser(user.getLogin());
        if(usr == null){
            throw new UsernameNotFoundException(String.format("User with login «%s» not found", user.getLogin()));
        }
        if(!user.getPassword().equals(usr.getPassword())){
            throw new UsernameNotFoundException(String.format("Wrong password for user «%s»", usr.getLogin()));
        }
        return usr;
    }

    public Usr register(Usr user){
        dbService.addUser(user);
        return user;
    }

    public Usr findByLogin(String login){
        Usr usr = dbService.getUser(login);
        return usr;
    }
}
