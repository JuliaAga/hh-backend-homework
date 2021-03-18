package ru.hh.school.service.favServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.SalaryDao;
import ru.hh.school.dao.VacancyDao;
import ru.hh.school.dto.VacancyDto;
import ru.hh.school.dto.hhDto.HhVacancyDto;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.dto.request.VacancyRequestDto;
import ru.hh.school.entity.*;
import ru.hh.school.mappers.AreaMapper;
import ru.hh.school.mappers.SalaryMapper;
import ru.hh.school.service.hhServices.HhVacancyService;


import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Service
public class FavVacancyService {
    private static final Logger logger = LoggerFactory.getLogger(FavVacancyService.class);

    VacancyDao vacancyDao;
    HhVacancyService hhVacancyService;
    FavEmployerService favEmployerService;
    AreaDao areaDao;
    Integer limitOfView;
    SalaryDao salaryDao;

    @Autowired
    public FavVacancyService(VacancyDao vacancyDao, HhVacancyService hhVacancyService,
                             AreaDao areaDao, FileSettings fileSettings,
                             FavEmployerService favEmployerService, SalaryDao salaryDao) {
        this.vacancyDao = vacancyDao;
        this.hhVacancyService = hhVacancyService;
        this.areaDao = areaDao;
        this.favEmployerService = favEmployerService;
        this.salaryDao = salaryDao;
        limitOfView = fileSettings.getInteger("hh.api.views.limit");
        if (limitOfView == null) {
            limitOfView = 50;
        }
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
            favEmployerService.increaseCounterOfView(vac.getEmployer());
        });
        return new VacancyDto(vacancyList, per_page, page);
    }

    @Transactional
    public void increaseCounterOfView(Vacancy vacancy) {
        vacancyDao.increaseCounterOfView(vacancy.getId());
        if (vacancy.getViewsCount() + 1 == limitOfView)
            setPopularityPopular(vacancy);
    }

    @Transactional
    public void setPopularityPopular(Vacancy vacancy) {
        vacancyDao.setPopularityPopular(vacancy.getId());
    }

    @Transactional
    public Vacancy saveVacancyWithInnerObjects(Vacancy vac) {
        salaryDao.saveOrUpdate(vac.getSalary());
        areaDao.saveOrUpdate(vac.getArea());
        return vacancyDao.save(vac);
    }

    @Transactional
    public Vacancy save(VacancyRequestDto dto) {

        Vacancy vac = getVacancyFromHh(dto.getVacancy_id());
        return saveVacancyWithInnerObjects(addDefaultFields(vac, dto.getComment()));
    }

    public Vacancy addDefaultFields(Vacancy vac, String comment) {
        vac.setComment(comment);
        vac.setViewsCount(0);
        vac.setDateCreate(LocalDate.now());
        vac.setPopularity(Popularity.REGULAR);
        return vac;
    }

    @Transactional
    public void refresh(Integer id) {

        Vacancy vacHH = getVacancyFromHh(id);

        Vacancy vacDb;
        try {
            vacDb = getByHhId(id);
            if (!vacHH.equals(vacDb)) {

                vacDb.setName(vacHH.getName());
                vacDb.setArea(vacHH.getArea());
                vacDb.setEmployer(vacHH.getEmployer());
                vacDb.setSalary(vacHH.getSalary());
                salaryDao.saveOrUpdate(vacHH.getSalary());
                areaDao.saveOrUpdate(vacHH.getArea());
                vacancyDao.save(vacDb);
            }
        } catch (NoResultException nre) {
            vacHH = addDefaultFields(vacHH, "Added when try refresh not existing in db");
            saveVacancyWithInnerObjects(vacHH);
        }

    }

    public Vacancy getVacancyFromHh(Integer id) {
        Vacancy vacancy = new Vacancy();

        HhVacancyDto hhVacancyDto = hhVacancyService.get(id);
        vacancy.setHhId(hhVacancyDto.getId());
        vacancy.setName(hhVacancyDto.getName());

        Area area = AreaMapper.areaDtoToArea(hhVacancyDto.getArea());
        vacancy.setArea(area);

        EmployerRequestDto empl = new EmployerRequestDto();
        empl.setComment("added because vacancy added");
        empl.setEmployer_id(hhVacancyDto.getEmployer().getId());
        Employer employer = favEmployerService.save(empl);

        vacancy.setEmployer(employer);

        Salary salary = SalaryMapper.salaryDtoToSalary(hhVacancyDto.getSalary());
        vacancy.setSalary(salary);

        return vacancy;
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
