package ru.hh.school.service.favServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dao.AreaDao;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dto.FavEmployersPageDto;
import ru.hh.school.dto.hhDto.HhEmployerDto;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.entity.Area;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Popularity;
import ru.hh.school.mappers.AreaMapper;
import ru.hh.school.service.hhServices.HhEmployerService;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Service
public class FavEmployerService {
    private static final Logger logger = LoggerFactory.getLogger(FavEmployerService.class);

    EmployerDao employerDao;
    HhEmployerService hhEmployerService;
    AreaDao areaDao;
    Integer limitOfView;

    @Autowired
    public FavEmployerService(EmployerDao employerDao, HhEmployerService hhEmployerService,
                              AreaDao areaDao, FileSettings fileSettings) {
        this.employerDao = employerDao;
        this.hhEmployerService = hhEmployerService;
        this.areaDao = areaDao;
        limitOfView = fileSettings.getInteger("hh.api.views.limit");
        if (limitOfView == null) {
            limitOfView = 50;
        }
    }

    @Transactional
    public Employer getFromDbByHhId(Integer id) {
        return employerDao.getByHhId(id);
    }

    @Transactional
    public FavEmployersPageDto getAll(Integer page, Integer per_page) {
        List<Employer> employerList = employerDao.getAll(page, per_page);
        employerList.forEach(empl -> {
            increaseCounterOfView(empl);
            if (empl.getViews_count() + 1 == limitOfView)
                //TODO получается если в настройках изменить границу то кто-то останется незаконно популярным (или обиженным)
                setPopularityPopular(empl);
        });
        return new FavEmployersPageDto(employerList, per_page, page);
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
    public Employer saveNewEmployerWithArea(Employer employer) {
        employer.setArea(areaDao.saveOrUpdate(employer.getArea()));
        return employerDao.save(employer);
    }

    @Transactional
    public void setComment(Integer id, String comment) {
        //TODO подкрутить ответ 404 на случай отсутствия в базе
        logger.info("Try to set comment for employer with id = " + id);
        employerDao.setComment(id, comment);
    }

    @Transactional
    public void delete(Integer id) {
        logger.info("Try to delete employer with id = " + id);
        employerDao.delete(id);
    }

    //todo не нужна бд, но дергает такие методы - почему не работает без transactional?
    @Transactional
    public Employer save(EmployerRequestDto dto) {

        Employer empl = getEmployerFromHh(dto.getEmployer_id());
        empl.setComment(dto.getComment());
        empl.setViews_count(0);
        empl.setDate_create(LocalDate.now());
        empl.setPopularity(Popularity.REGULAR);
        return saveNewEmployerWithArea(empl);
    }

    @Transactional
    public void refresh(Integer id) {
        //ставлю получение от хх первым шагом - если упадет, то дальше не поедет - игра не стоит свеч без источника
        Employer emplHH = getEmployerFromHh(id);
        //в теории если такого мы раньше не сохраняли и пытаемся обновить - надо бы ошибку
        //но я думаю логичнее сохранить как нового
        Employer emplDb;
        try {
            emplDb = getFromDbByHhId(id);
            if (!emplHH.equals(emplDb)) {
                emplDb.setName(emplHH.getName());
                emplDb.setDescription(emplHH.getDescription());
                emplDb.setArea(emplHH.getArea());
                saveNewEmployerWithArea(emplDb);
            }
        } catch (NoResultException nre) {
            saveNewEmployerWithArea(emplHH);
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

}
