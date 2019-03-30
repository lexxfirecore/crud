package com.lexx.demo.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import com.lexx.demo.entity.User;
import com.lexx.demo.service.DBLoginException;

/**
 * Created by alexandruco on 28-Mar-19.
 */
public class UserDao {
    static final String H2CONF = "hibernate.cfg.xml";

    public static SessionFactory getSessionFactory() {
        Configuration config = new Configuration();

        config.configure(H2CONF);
        config.addAnnotatedClass(User.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static User read(int id, String password) throws DBLoginException {
        Session session = getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    public static User read(String username, String password) throws DBLoginException {
        final String entityField = "name";
        Session session = getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(entityField, username));
        User user = (User) criteria.uniqueResult();
        session.close();
        return user;
    }

    public static List<User> readAll() {
        Session session = getSessionFactory().openSession();
        List<User> users = session.createCriteria(User.class).list();
        session.close();
        return users;
    }

    public static int create(User user) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return user.getId();
    }
}
