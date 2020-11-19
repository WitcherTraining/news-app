package com.epam.spring.services.dao;

import com.epam.spring.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<User> theQuery = currentSession.createQuery("from User order by firstName ", User.class);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public User findById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(User.class, id);
    }

    @Override
    @Transactional
    public User save(User object) {
        Session currentSession = sessionFactory.getCurrentSession();
//        currentSession.saveOrUpdate(object);
        return (User) currentSession.save(object);
    }

    @Override
    @Transactional
    public void delete(User object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from User where id=:userId");

        theQuery.setParameter("userId", id);

        theQuery.executeUpdate();
    }

    @Override
    @Transactional
    public User findByLastName(String lastName) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("FROM User where lastName =: lastName");

        theQuery.setParameter("lastName", lastName);

        return (User) theQuery.getSingleResult();
    }
}
