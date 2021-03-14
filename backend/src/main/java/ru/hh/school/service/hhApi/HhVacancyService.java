package ru.hh.school.service.hhApi;

import org.springframework.stereotype.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.HhException;
import ru.hh.school.dto.hhResponse.VacanciesPageResponseDto;
import ru.hh.school.dto.hhResponse.VacancyResponseDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

@Service
public class HhVacancyService {
    private String REQUEST_URL;

    Client client;
    FileSettings fileSettings;

    public HhVacancyService(FileSettings fileSettings, Client client) {
        REQUEST_URL = fileSettings.getString("hh.api.vacancies");
        this.client = client;
    }

    public VacancyResponseDto get(Integer id) {
        Response response = client.target(REQUEST_URL + "/" + id).request().get();
        int status = response.getStatus();
        if (status == 200) {
            VacancyResponseDto entity = response.readEntity(VacancyResponseDto.class);
            return entity;
        } else
            throw new HhException(String.valueOf(status));

    }

    public VacanciesPageResponseDto getAll(Integer page, Integer per_page, String query) {


        StringBuffer url = new StringBuffer();
        //TODO rewrite this
        url.append(REQUEST_URL)
                .append("?");
        if (page != null)
            url.append("page=" + page+"&");
        if (per_page != null)
            url.append("per_page=" + per_page+"&");
        if (query != null)
            url.append("text=" + query);

        Response response = client.target(url.toString()).request().get();
        int status = response.getStatus();
        if (status == 200) {
            VacanciesPageResponseDto entities= response.readEntity(VacanciesPageResponseDto.class);
            return entities;
        } else
            throw new HhException(String.valueOf(status));
    }

}
