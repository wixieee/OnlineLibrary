package edu.library.service;

import edu.library.dto.AuthRequest;
import edu.library.dto.RegisterRequest;
import edu.library.model.User;

public interface UserService {
    User findByUsername(String username);
    User validateCredentials(AuthRequest authRequest);
    User registerUser(RegisterRequest registerRequest);
}
