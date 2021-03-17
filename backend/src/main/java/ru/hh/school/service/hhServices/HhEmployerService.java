package ru.hh.school.service.hhServices;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.exception.HhException;
import ru.hh.school.dto.hhDto.HhEmployersPageDto;
import ru.hh.school.dto.hhDto.HhEmployerDto;
import ru.hh.school.exception.HhExceptionHandler;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Service
public class HhEmployerService {
    private static final Logger logger = LoggerFactory.getLogger(HhEmployerService.class);

    private String EMPL_ENDPOINT;

    Client client;

    public HhEmployerService(FileSettings fileSettings, Client client) {
        EMPL_ENDPOINT = fileSettings.getString("hh.api.employers");
        if (EMPL_ENDPOINT == null)
            EMPL_ENDPOINT = "https://api.hh.ru/employers";
        this.client = client;
    }

    public HhEmployerDto get(Integer id) {
        logger.info("Send request to " + EMPL_ENDPOINT + " with path = " + id);
        Response response = client.target(EMPL_ENDPOINT + "/" + id).request().get();
        int status = response.getStatus();
        if (status == 200) {
            return response.readEntity(HhEmployerDto.class);
        } else
            throw new HhException(String.valueOf(status));

    }

    public HhEmployersPageDto getAll(Integer page, Integer per_page, String query) {
        URI uri = UriBuilder.fromUri(EMPL_ENDPOINT)
                .queryParam("page", page)
                .queryParam("per_page", per_page)
                .queryParam("text", query)
                .build();
        logger.info("Send request " + uri);
        Response response = client.target(uri).request().get();
        int status = response.getStatus();
        if (status == 200) {
            return response.readEntity(HhEmployersPageDto.class);
        } else
            throw new HhException(String.valueOf(status));
    }

}
