package com.epam.spring.services.dao;

import com.epam.spring.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CategoryDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Category> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Category> theQuery = currentSession.createQuery("from Category order by name ", Category.class);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public Category findById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Category.class, id);
    }

    @Override
    @Transactional
    public void save(Category object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(object);
    }

    @Override
    @Transactional
    public void update(Category object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(object);
    }

    @Override
    @Transactional
    public void delete(Category object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from Category where id=:categoryId");

        theQuery.setParameter("categoryId", id);

        theQuery.executeUpdate();
    }
}
