package com.joshrincon.service;

import com.joshrincon.domain.User;
import com.joshrincon.util.UserExistException;


public interface IService {
    void register(User user) throws UserExistException;
    User login(String username, String password);
}
