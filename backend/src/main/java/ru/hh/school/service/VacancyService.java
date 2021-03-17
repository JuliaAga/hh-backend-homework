package ru.hh.school.service;

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


import java.time.LocalDate;
import java.util.List;

@Service
public class VacancyService {
    VacancyDao vacancyDao;
    HhVacancyService hhVacancyService;
    EmployerService employerService;
    AreaDao areaDao;
    FileSettings fileSettings;
    Integer limitOfView;
    SalaryDao salaryDao;

    @Autowired
    public VacancyService(VacancyDao vacancyDao, HhVacancyService hhVacancyService,
                          AreaDao areaDao, FileSettings fileSettings,
                          EmployerService employerService, SalaryDao salaryDao) {
        this.vacancyDao = vacancyDao;
        this.hhVacancyService = hhVacancyService;
        this.areaDao = areaDao;
        this.fileSettings = fileSettings;
        this.employerService = employerService;
        this.salaryDao = salaryDao;
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
            Employer empl = vac.getEmployer();
            //todo this is wrong decision
            employerService.increaseCounterOfView(empl);
            if (empl.getViews_count() + 1 == limitOfView)
                employerService.setPopularityPopular(empl);
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
        salaryDao.saveOrUpdate(vac.getSalary());
        areaDao.saveOrUpdate(vac.getArea());
        return vacancyDao.save(vac);
    }

    @Transactional
    public void refresh(Integer id) {

        Vacancy vacHH = getVacancyFromHh(id);

        Vacancy vacDb = getByHhId(id);

        if (!vacHH.equals(vacDb)) {

            vacDb.setName(vacHH.getName());
            vacDb.setArea(vacHH.getArea());
            vacDb.setEmployer(vacHH.getEmployer());
            vacDb.setSalary(vacHH.getSalary());
            salaryDao.saveOrUpdate(vacHH.getSalary());
            areaDao.saveOrUpdate(vacHH.getArea());
            vacancyDao.save(vacDb);
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
        Employer employer = employerService.save(empl);
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
