package com.joshrincon.service.impl;

import com.joshrincon.dao.impl.UserDAO;
import com.joshrincon.domain.User;
import com.joshrincon.service.IService;
import com.joshrincon.util.ServiceUtil;
import com.joshrincon.util.UserExistException;


public class BusinessService implements IService {

    private UserDAO dao = new UserDAO();

    @Override
    public void register(User user) throws UserExistException {

        //TODO: implement hasUser if case

        user.setPassword(ServiceUtil.md5(user.getPassword()));
        dao.addUser(user);
    }

    @Override
    public User login(String username, String password) {
        password = ServiceUtil.md5(password);
        return dao.findUser(username, password);
    }
}
