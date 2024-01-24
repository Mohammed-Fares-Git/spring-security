package com.mohammedfares.spring_security.services;

import com.mohammedfares.spring_security.comon.Utils;
import com.mohammedfares.spring_security.dto.UserDto;
import com.mohammedfares.spring_security.entities.UserEntity;
import com.mohammedfares.spring_security.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;
    @Autowired
    BCryptPasswordEncoder bCryptPassswordEncoder;


    public UserDto createUser(UserDto user) {

        UserEntity dejaExist = userRepository.findByEmail(user.getEmail());

        if (dejaExist != null) throw new RuntimeException("User Already Exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setUserId(utils.generateUserId(20));
        userEntity.setEncryptedPassword(bCryptPassswordEncoder.encode(user.getPassword()));

        UserEntity savedUser = userRepository.save(userEntity);

        UserDto feedBack = new UserDto();

        BeanUtils.copyProperties(savedUser, feedBack);

        return feedBack;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(username);


        return new User(user.getEmail(),user.getEncryptedPassword(),new ArrayList<>());
    }
}
