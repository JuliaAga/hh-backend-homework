package ru.hh.school.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.dto.response.EmployerResponseDto;

@Service
public class OuterEmployerService {
    private String path = "employers";

    FileSettings fileSettings;

    public OuterEmployerService(FileSettings fileSettings) {
        path += fileSettings.getString("hh.api");
    }

    public EmployerResponseDto get (Long id) {
        //todo go to hh and get result
        //todo map result to doto
        return new EmployerResponseDto();
    }
/*
    public EmployerResponseDto getAll (Integer page, @RequestParam Integer per_page) {
        //todo go to hh and get result
        //todo map result to doto
        return new EmployerResponseDto();
    }
*/
}
