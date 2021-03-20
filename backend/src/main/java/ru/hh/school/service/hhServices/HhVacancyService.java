package ru.hh.school.service.hhServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.exception.HhException;
import ru.hh.school.dto.hhDto.HhVacanciesPageDto;
import ru.hh.school.dto.hhDto.HhVacancyDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Service
public class HhVacancyService {
    private static final Logger logger = LoggerFactory.getLogger(HhVacancyService.class);

    private String VAC_ENDPOINT;

    Client client;

    public HhVacancyService(FileSettings fileSettings, Client client) {
        VAC_ENDPOINT = fileSettings.getString("hh.api.vacancies");
        if (VAC_ENDPOINT == null)
            VAC_ENDPOINT = "https://api.hh.ru/vacancies";
        this.client = client;
    }

    public HhVacancyDto get(Integer id) {
        logger.info("Send request to " + VAC_ENDPOINT + " with path = " + id);
        Response response = client.target(VAC_ENDPOINT + "/" + id).request().get();
        int status = response.getStatus();
        if (status == 200) {
            return response.readEntity(HhVacancyDto.class);
        } else
            throw new HhException(String.valueOf(status));

    }

    public HhVacanciesPageDto getAll(Integer page, Integer per_page, String query) {
        URI uri = UriBuilder.fromUri(VAC_ENDPOINT)
                .queryParam("page", page)
                .queryParam("per_page", per_page)
                .queryParam("text", query)
                .build();
        logger.info("Send request " + uri);
        Response response = client.target(uri).request().get();
        int status = response.getStatus();
        if (status == 200) {
            return response.readEntity(HhVacanciesPageDto.class);
        } else
            throw new HhException(String.valueOf(status));
    }

}
