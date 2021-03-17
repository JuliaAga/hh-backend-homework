package ru.hh.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.EmployerDto;
import ru.hh.school.dto.hhDto.HhEmployerDto;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Popularity;
import ru.hh.school.mappers.AreaMapper;
import ru.hh.school.service.hhServices.HhEmployerService;

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
    public Employer getByHhId(Integer id) {
        return employerDao.getByHhId(id);
    }


    @Transactional
    public EmployerDto getAll(Integer page, Integer per_page) {
        List<Employer> employerList = employerDao.getAll(page, per_page);
        employerList.forEach(empl -> {
            increaseCounterOfView(empl);
            if (empl.getViews_count() + 1 == limitOfView)
                //TODO получается если в настройках увеличить границу то кто-то останется незаконно популярным
                setPopularityPopular(empl);
        });
        return new EmployerDto(employerList, per_page, page);
    }

    @Transactional
    public void increaseCounterOfView(Employer employer) {
        employerDao.increaseCounterOfView(employer.getId());
    }

    @Transactional
    public void setPopularityPopular(Employer employer) {
        employerDao.setPopularityPopular(employer.getId());
    }

    @Transactional
    public Employer save(EmployerRequestDto dto) {

        Employer empl = getEmployerFromHh(dto.getEmployer_id());

        empl.setComment(dto.getComment());
        empl.setViews_count(0);
        empl.setDate_create(LocalDate.now());
        empl.setPopularity(Popularity.REGULAR);

        areaDao.saveOrUpdate(empl.getArea());
        return employerDao.save(empl);
    }

    @Transactional
    public void refresh(Integer id) {

        Employer emplHH = getEmployerFromHh(id);

        Employer emplDb = getByHhId(id);

        if (!emplHH.equals(emplDb)){
            emplDb.setName(emplHH.getName());
            emplDb.setDescription(emplHH.getDescription());
            emplDb.setArea(emplHH.getArea());

            areaDao.saveOrUpdate(emplHH.getArea());
            employerDao.save(emplDb);
        }

    }

    public Employer getEmployerFromHh(Integer id) {
        HhEmployerDto hhEmployerDto = hhEmployerService.get(id);

        Area area = AreaMapper.areaDtoToArea(hhEmployerDto.getArea());
        Employer employer = new Employer();

        employer.setHhId(id);

        employer.setArea(area);
        employer.setName(hhEmployerDto.getName());
        employer.setDescription(hhEmployerDto.getDescription());

        return employer;
    }


    @Transactional
    public void setComment(Integer id, String comment) {
        employerDao.setComment(id, comment);
    }

    @Transactional
    public void delete(Integer id) {
        employerDao.delete(id);
    }

}
