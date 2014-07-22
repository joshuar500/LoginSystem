package com.joshrincon.dao.impl;

import com.joshrincon.dao.IUserDAO;
import com.joshrincon.domain.User;
import com.joshrincon.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by on 7/21/2014.
 */
public class UserDAO implements IUserDAO {

    private SessionFactory sf = HibernateUtil.createSessionFactory();

    @Override
    public void addUser(User user) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sf.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to add user");
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public User findUser(String username, String password) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sf.openSession();
            System.out.println("session open");
            transaction = session.beginTransaction();
            System.out.println("begin transaction");

            System.out.println("begin finding users");
            Query query = session.createQuery("from User where username=? and password=?");
            System.out.println("from user where username " + username + " and password " + password);
            query.setParameter(0, username);
            query.setParameter(1, password);
            System.out.println("might have found users...");
            List<User> result = query.list();
            transaction.commit();
            return result.size() > 0 ? result.get(0) : null;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Failed to find user");
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
