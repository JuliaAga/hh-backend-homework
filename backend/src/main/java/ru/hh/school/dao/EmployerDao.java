package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Employer;

import java.util.List;

@Repository
public class EmployerDao {

  SessionFactory sessionFactory;

  public EmployerDao(SessionFactory sessionFactory) {
    this.sessionFactory =  sessionFactory;
  }

  public Employer get(Long id) {
    Session session = getSession();
    Employer employer = session
            .createQuery("SELECT f FROM Employer f WHERE id = :id", Employer.class)
            .setParameter("id", id)
            .getSingleResult();
    return employer;
  }

  public List<Employer> getAll(Integer page, Integer per_page) {
    Session session = getSession();
    return session
            .createQuery("SELECT f FROM Employer f order by id", Employer.class)
            .setFirstResult(page * per_page)
            .setMaxResults(per_page)
            .list();

  }

  public Employer save(Employer employer) {
    getSession().save(employer);
    return employer;
  }


  private Session getSession() {
    try {
      return sessionFactory.getCurrentSession();
    } catch (Exception e) {
      return sessionFactory.openSession();
    }
  }
}
