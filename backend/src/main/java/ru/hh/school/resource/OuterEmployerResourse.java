package ru.hh.school.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hh.school.dto.response.EmployerResponseDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.service.OuterEmployerService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class OuterEmployerResourse {
    OuterEmployerService outerEmployerService;

    public OuterEmployerResourse(OuterEmployerService outerEmployerService) {
        this.outerEmployerService = outerEmployerService;
    }

    @GET
    @Path("/employer/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployerResponseDto get(@RequestParam Long id) {
        return outerEmployerService.get(id);
    }
/*
    @GET
    @Path("/employer")
    @Consumes(MediaType.APPLICATION_JSON)
    public EmployerResponseDto getAll(@RequestParam Integer page, @RequestParam Integer per_page) {
        //todo change return type
        if (page == null) page = 0;
        if (page == null) per_page = 20;
        return outerEmployerService.getAll(page, per_page);
    }*/
}
