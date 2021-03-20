package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Salary;

@Repository
public class SalaryDao {

  SessionFactory sessionFactory;

  public SalaryDao(SessionFactory sessionFactory) {
    this.sessionFactory =  sessionFactory;
  }

  public Salary get(Integer id) {
    Session session = getSession();
    Salary salary = session
            .createQuery("SELECT a FROM Salary a WHERE id = :id", Salary.class)
            .setParameter("id", id)
            .getSingleResult();
    return salary;
  }

  public Salary saveOrUpdate(Salary salary) {
    sessionFactory.getCurrentSession().saveOrUpdate(salary);
    return salary;
  }


  private Session getSession() {
    try {
      return sessionFactory.getCurrentSession();
    } catch (Exception e) {
      return sessionFactory.openSession();
    }
  }
}
