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

  public Employer getByHhId(Integer id) {
    Session session = getSession();
    return session
            .createQuery("SELECT f FROM Employer f WHERE hh_id = :id", Employer.class)
            .setParameter("id", id)
            .getSingleResult();
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

  public void increaseCounterOfView(Integer id) {
    sessionFactory.getCurrentSession()
            .createQuery("update Employer set views_count = views_count + 1 WHERE id = :id")
            .setParameter("id", id)
            .executeUpdate();

  }

  public void setPopularityPopular(Integer id) {
    sessionFactory.getCurrentSession()
            .createQuery("update Employer set popularity = 'POPULAR' WHERE hh_id = :id")
            .setParameter("id", id)
            .executeUpdate();
  }

  public void setComment(Integer id, String comment) {
    sessionFactory.getCurrentSession()
            .createQuery("update Employer set comment = :comment WHERE hh_id = :id")
            .setParameter("id", id)
            .setParameter("comment", comment)
            .executeUpdate();
  }

  public void delete(Integer id) {
    sessionFactory.getCurrentSession()
            .createQuery("delete from Employer WHERE hh_id = :id")
            .setParameter("id", id)
            .executeUpdate();
  }

  private Session getSession() {
    try {
      return sessionFactory.getCurrentSession();
    } catch (Exception e) {
      return sessionFactory.openSession();
    }
  }


}
