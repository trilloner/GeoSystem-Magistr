package com.geosystem.springbootbackend.services;

import com.geosystem.springbootbackend.dto.UserRequest;
import com.geosystem.springbootbackend.models.User;
import com.geosystem.springbootbackend.models.UserDetailsImpl;
import com.geosystem.springbootbackend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public User saveUser(UserRequest userRequest) throws Exception {
        User userToDB = new User();
        userToDB.setName(userRequest.getName());
        userToDB.setEmail(userRequest.getEmail());
        userToDB.setDate(userRequest.getDate());
        userToDB.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        return userRepository.save(userToDB);
    }


    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long user_id) {
        return userRepository.findById(user_id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with this email does not exist"));
        return new UserDetailsImpl(user);
    }
}
