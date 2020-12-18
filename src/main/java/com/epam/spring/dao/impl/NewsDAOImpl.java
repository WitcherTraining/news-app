package com.epam.spring.dao.impl;

import com.epam.spring.dao.NewsDAO;
import com.epam.spring.model.News;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
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
    public News save(News object) {
        try (Session currentSession = sessionFactory.getCurrentSession()) {
            currentSession.beginTransaction();
            Serializable savedNews = currentSession.save(object);
            currentSession.getTransaction().commit();
            return (News) savedNews;
        }
    }

    @Override
    @Transactional
    public News saveOrUpdateNews(News news) {
        try (final Session currentSession = sessionFactory.getCurrentSession()) {

            currentSession.beginTransaction();
            currentSession.saveOrUpdate(news);
            currentSession.getTransaction().commit();

            return news;
        }

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
