package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class GenericDao {
  private final SessionFactory sessionFactory;

  public GenericDao(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public <T> T get(Class<T> clazz, Serializable id) {
    return getSession().get(clazz, id);
  }

  public <T> void delete(Class<T> clazz, Serializable id) {
    T objToDelete = get(clazz, id);
    getSession().delete(objToDelete);
  }

  public <T> List<T> getAll(Class<T> clazz, Integer page, Integer per_page) {
    return
    getSession().createQuery("From "+clazz)
    .setFirstResult(0)
    .setMaxResults(10)
            .list();
  }

  public void save(Object object) {
    if (object == null) {
      return;
    }
    getSession().save(object);
  }


  public void update(Object object){
    if (object == null) {
      return;
    }
    getSession().update(object);
  }


  protected Session getSession() {
    try {
      return sessionFactory.getCurrentSession();
    } catch (Exception e) {
      return sessionFactory.openSession();
    }
  }
}
