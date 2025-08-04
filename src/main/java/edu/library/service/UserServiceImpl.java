package edu.library.service;

import edu.library.dto.AuthRequest;
import edu.library.dto.RegisterRequest;
import edu.library.exception.ResourceNotFoundException;
import edu.library.model.Role;
import edu.library.model.User;
import edu.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User validateCredentials(AuthRequest authRequest) {
        User user = findByUsername(authRequest.getUsername());

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        return user;
    }

    @Override
    public User registerUser(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        User register = new User();
        register.setUsername(registerRequest.getUsername());
        register.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        register.setRole(Role.USER);
        register.setEmail(registerRequest.getEmail());

        return userRepository.save(register);
    }
}
