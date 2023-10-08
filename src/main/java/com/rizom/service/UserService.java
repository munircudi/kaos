package com.rizom.service;

import com.rizom.excption.RizomRuntimeException;
import com.rizom.model.User;
import com.rizom.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;


    public void save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RizomRuntimeException("Kullanıcı adı zaten kullanılıyor: " + user.getUsername());
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public User getUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        User user;
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RizomRuntimeException(" user not found for id :: " + id);
        }
        return user;
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}