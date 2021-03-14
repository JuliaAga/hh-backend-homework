package ru.hh.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.dto.hhResponse.VacancyResponseDto;
import ru.hh.school.dto.request.VacancyRequestDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.entity.Popularity;
import ru.hh.school.mappers.AreaMapper;
import ru.hh.school.service.hhApi.HhVacancyService;


import java.time.LocalDate;
import java.util.List;

@Service
public class VacancyService {
    VacancyDao vacancyDao;
    HhVacancyService hhVacancyService;
    AreaDao areaDao;
    FileSettings fileSettings;
    Integer limitOfView;

    @Autowired
    public VacancyService(VacancyDao vacancyDao, HhVacancyService hhVacancyService,
                          AreaDao areaDao, FileSettings fileSettings) {
        this.vacancyDao = vacancyDao;
        this.hhVacancyService = hhVacancyService;
        this.areaDao = areaDao;
        this.fileSettings = fileSettings;
        limitOfView = fileSettings.getInteger("hh.api.views.limit");
    }

    @Transactional
    public Vacancy getByHhId(Integer id) {
        return vacancyDao.getByHhId(id);
    }


    @Transactional
    public VacancyDto getAll(Integer page, Integer per_page) {
        List<Vacancy> vacancyList = vacancyDao.getAll(page, per_page);
        vacancyList.forEach(vac -> {
            increaseCounterOfView(vac);
            if (vac.getViews_count() + 1 == limitOfView)
                //TODO получается если в настройках увеличить границу то кто-то останется незаконно популярным
                setPopularityPopular(vac);
        });
        return new VacancyDto(vacancyList, per_page, page);
    }

    @Transactional
    public void increaseCounterOfView(Vacancy vacancy) {
        vacancyDao.increaseCounterOfView(vacancy.getId());
    }

    @Transactional
    public void setPopularityPopular(Vacancy vacancy) {
        vacancyDao.setPopularityPopular(vacancy.getId());
    }

    @Transactional
    public Vacancy save(VacancyRequestDto dto) {

        Vacancy vac = getVacancyFromHh(dto.getVacancy_id());

        vac.setComment(dto.getComment());
        vac.setViews_count(0);
        vac.setDate_create(LocalDate.now());
        vac.setPopularity(Popularity.REGULAR);
        //TODO добавить сохранение employer
        areaDao.saveOrUpdate(vac.getArea());
        return vacancyDao.save(vac);
    }

    @Transactional
    public void refresh(Integer id) {

        Vacancy vacHH = getVacancyFromHh(id);

        Vacancy vacDb = getByHhId(id);

        if (!vacHH.equals(vacDb)){

            vacDb.setName(vacHH.getName());
            vacDb.setArea(vacHH.getArea());
            vacDb.setEmployer(vacHH.getEmployer());
            vacDb.setSalary(vacHH.getSalary());
            //TODO добавить сохранение salary
            areaDao.saveOrUpdate(vacHH.getArea());
            vacancyDao.save(vacDb);
        }

    }

    public Vacancy getVacancyFromHh(Integer id) {
        //TODO

        return new Vacancy();
    }


    @Transactional
    public void setComment(Integer id, String comment) {
        vacancyDao.setComment(id, comment);
    }

    @Transactional
    public void delete(Integer id) {
        vacancyDao.delete(id);
    }

}
