package ru.hh.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.GenericDao;

import ru.hh.school.entity.Vacancy;

import java.util.List;

@Service
public class VacancyService {
    GenericDao genericDao;

    @Autowired
    public VacancyService(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Transactional
    public Vacancy get(Long id) {
        return genericDao.get(Vacancy.class, id);
    }

    @Transactional
    public List<Vacancy> getAll(Integer page, Integer per_page) {
        return genericDao.getAll(Vacancy.class, page, per_page);
    }

    //TODO
    @Transactional
    public void IncreaseCounterOfView(List<Vacancy> vacancies) {

    }

    //TODO
    @Transactional
    public void save(Vacancy vacancy) {

    }

    //TODO
    @Transactional
    public void refresh(Vacancy vacancy) {

    }

    @Transactional
    public void delete(Long id) {
        genericDao.delete(Vacancy.class, id);
    }
}
