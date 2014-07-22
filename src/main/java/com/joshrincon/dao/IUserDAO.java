package com.joshrincon.dao;

import com.joshrincon.domain.User;

/**
 * Created by on 7/21/2014.
 */
public interface IUserDAO {
    void addUser(User user);
    User findUser(String username, String password);
}
