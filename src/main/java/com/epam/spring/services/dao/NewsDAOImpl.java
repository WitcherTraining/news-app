package com.epam.spring.services.dao;

import com.epam.spring.model.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public NewsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<News> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<News> theQuery = currentSession.createQuery("from News order by date ", News.class);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public News findById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(News.class, id);
    }

    @Override
    @Transactional
    public void save(News object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(object);
    }

    @Override
    @Transactional
    public void update(News object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(object);
    }

    @Override
    @Transactional
    public void delete(News object) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(object);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = currentSession.createQuery("delete from News where id=:newsId");

        theQuery.setParameter("newsId", id);

        theQuery.executeUpdate();
    }
}
