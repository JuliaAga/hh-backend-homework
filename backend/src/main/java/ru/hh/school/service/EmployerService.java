package ru.hh.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.hhResponse.EmployerResponseDto;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Popularity;
import ru.hh.school.mappers.AreaMapper;
import ru.hh.school.service.hhApi.HhEmployerService;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployerService {
    EmployerDao employerDao;
    HhEmployerService hhEmployerService;
    AreaDao areaDao;
    FileSettings fileSettings;
    Integer limitOfView;

    @Autowired
    public EmployerService(EmployerDao employerDao, HhEmployerService hhEmployerService,
                           AreaDao areaDao, FileSettings fileSettings) {
        this.employerDao = employerDao;
        this.hhEmployerService = hhEmployerService;
        this.areaDao = areaDao;
        this.fileSettings = fileSettings;
        limitOfView = fileSettings.getInteger("hh.api.views.limit");
    }

    @Transactional
    public Employer get(Long id) {
        return employerDao.get(id);
    }

    @Transactional
    public List<Employer> getAll(Integer page, Integer per_page) {
        List<Employer> employerList = employerDao.getAll(page, per_page);
        employerList.forEach(empl -> {
            increaseCounterOfView(empl);
            if (empl.getViews_count() + 1 == limitOfView)
                setPopularityPopular(empl);
        });
        return employerList;
    }

    @Transactional
    public void increaseCounterOfView(Employer employer) {
        employerDao.increaseCounterOfView(employer.getId());
    }

    @Transactional
    public void setPopularityPopular(Employer employer) {
        employerDao.setPopularityPopular(employer.getId());
    }

    //TODO
    @Transactional
    public Employer save(EmployerRequestDto dto) {

        EmployerResponseDto employerResponseDto = hhEmployerService.get(dto.getEmployer_id());

        //TODO вынести в отдельный сервис?
        Area area = AreaMapper.areaDtoToArea(employerResponseDto.getArea());
        areaDao.saveOrUpdate(area);
        Employer employer = new Employer();

        employer.setHhId(dto.getEmployer_id());
        employer.setComment(dto.getComment());

        employer.setArea(area);
        employer.setName(employerResponseDto.getName());
        employer.setDescription(employerResponseDto.getDescription());

        //TODO А ЕСЛИ ОН УЖЕ БЫЛ В БАЗЕ?
        employer.setViews_count(0);
        employer.setDate_create(LocalDate.now());
        employer.setPopularity(Popularity.REGULAR);
        return  employerDao.save(employer);
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
