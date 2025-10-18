package java412.galleryapp.service;

import java412.galleryapp.entity.Role;
import java412.galleryapp.entity.User;
import java412.galleryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

@Service
@Validated
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class)
    public void registerUser(String username, String password, String email) throws Exception {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new Exception("Пользователь уже существует");
        }

        User user = new User();

        user.setId(UUID.randomUUID());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        user.setEmail(email);

        userRepository.saveAndFlush(user);

    }

    @Transactional(rollbackFor = Exception.class)
    public void changePassword(User user) {
        userRepository.saveAndFlush(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

}
