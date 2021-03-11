package ru.hh.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.GenericDao;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.entity.Employer;

import java.util.List;

@Service
public class EmployerService {
    EmployerDao genericDao;

    @Autowired
    public EmployerService( EmployerDao genericDao) {
        this.genericDao = genericDao;
    }

    @Transactional
    public Employer get(Long id) {
        return genericDao.get(id);
    }

    @Transactional
    public List<Employer> getAll(Integer page, Integer per_page) {
        return genericDao.getAll(page, per_page);
    }

    //TODO
    @Transactional
    public void IncreaseCounterOfView(List<Employer> employers) {

    }

    //TODO
    @Transactional
    public Employer save(EmployerRequestDto dto) {
        //todo получить от хх работадателя
        //todo преобразовать его в свой вид
        //todo сохранить в бд
        //todo вернуть созданный объект
        return new Employer();
    }

    //TODO
    @Transactional
    public Employer refresh(Employer employer) {
        //todo по ID получить от хх
        //todo обновить поля
        //todo сохранить в бд
        //todo вернуть созданный объект
        return new Employer();
    }

  /*  @Transactional
    public void delete(Long id) {
        genericDao.delete(id);
    }*/
}
