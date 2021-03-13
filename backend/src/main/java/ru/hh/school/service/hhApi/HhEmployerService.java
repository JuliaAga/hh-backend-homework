package ru.hh.school.service.hhApi;

import org.springframework.stereotype.Service;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.school.HhException;
import ru.hh.school.dto.hhResponse.EmployerPageResponseDto;
import ru.hh.school.dto.hhResponse.EmployerResponseDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

@Service
public class HhEmployerService {
    private String REQUEST_URL;

    Client client;
    FileSettings fileSettings;

    public HhEmployerService(FileSettings fileSettings, Client client) {
        REQUEST_URL = fileSettings.getString("hh.api.employers");
        this.client = client;
    }

    public EmployerResponseDto get(Integer id) {
        Response response = client.target(REQUEST_URL + "/" + id).request().get();
        int status = response.getStatus();
        if (status == 200) {
            EmployerResponseDto entity = response.readEntity(EmployerResponseDto.class);
            return entity;
        } else
            throw new HhException(String.valueOf(status));

    }

    public EmployerPageResponseDto getAll(Integer page, Integer per_page, String query) {


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
            EmployerPageResponseDto entities= response.readEntity(EmployerPageResponseDto.class);
            return entities;
        } else
            throw new HhException(String.valueOf(status));
    }

}
