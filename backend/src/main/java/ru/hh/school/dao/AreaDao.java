package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;

import java.util.List;

@Repository
public class AreaDao {

  SessionFactory sessionFactory;

  public AreaDao(SessionFactory sessionFactory) {
    this.sessionFactory =  sessionFactory;
  }

  public Area get(Integer id) {
    Session session = getSession();
    Area area = session
            .createQuery("SELECT a FROM Area a WHERE id = :id", Area.class)
            .setParameter("id", id)
            .getSingleResult();
    return area;
  }

  public Area saveOrUpdate(Area area) {
    sessionFactory.getCurrentSession().saveOrUpdate(area);
    return area;
  }


  private Session getSession() {
    try {
      return sessionFactory.getCurrentSession();
    } catch (Exception e) {
      return sessionFactory.openSession();
    }
  }
}
