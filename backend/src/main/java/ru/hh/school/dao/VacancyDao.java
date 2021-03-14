package ru.hh.school.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.hh.school.entity.Vacancy;

import java.util.List;

@Repository
public class VacancyDao {

    SessionFactory sessionFactory;

    public VacancyDao(SessionFactory sessionFactory) {
        this.sessionFactory =  sessionFactory;
    }

    public Vacancy getByHhId(Integer id) {
        Session session = getSession();
        Vacancy vacancy = session
                .createQuery("SELECT f FROM Vacancy f WHERE hh_id = :id", Vacancy.class)
                .setParameter("id", id)
                .getSingleResult();
        return vacancy;
    }

    public List<Vacancy> getAll(Integer page, Integer per_page) {
        Session session = getSession();
        return session
                .createQuery("SELECT f FROM Vacancy f order by id", Vacancy.class)
                .setFirstResult(page * per_page)
                .setMaxResults(per_page)
                .list();
    }

    public Vacancy save(Vacancy Vacancy) {
        getSession().save(Vacancy);
        return Vacancy;
    }

    public void increaseCounterOfView(Integer id) {
        sessionFactory.getCurrentSession()
                .createQuery("update Vacancy set views_count = views_count + 1 WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void setPopularityPopular(Integer id) {
        sessionFactory.getCurrentSession()
                .createQuery("update Vacancy set popularity = 'POPULAR' WHERE hh_id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public void setComment(Integer id, String comment) {
        sessionFactory.getCurrentSession()
                .createQuery("update Vacancy set comment = :comment WHERE hh_id = :id")
                .setParameter("id", id)
                .setParameter("comment", comment)
                .executeUpdate();
    }

    public void delete(Integer id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Vacancy WHERE hh_id = :id")
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
