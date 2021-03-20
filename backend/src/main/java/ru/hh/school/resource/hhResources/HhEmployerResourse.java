package ru.hh.school.resource.hhResources;


import com.sun.istack.NotNull;
import org.springframework.stereotype.Controller;
import ru.hh.school.dto.hhDto.HhEmployerDto;
import ru.hh.school.dto.hhDto.HhEmployersPageDto;
import ru.hh.school.exception.ExceptionHandler;
import ru.hh.school.service.hhServices.HhEmployerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employer")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class HhEmployerResourse {

    HhEmployerService hhEmployerService;

    public HhEmployerResourse(HhEmployerService hhEmployerService) {
        this.hhEmployerService = hhEmployerService;
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@NotNull @PathParam("id") Integer id) {
        return ExceptionHandler.handleException(() -> {
            HhEmployerDto empl = hhEmployerService.get(id);
            return Response.ok(empl).build();
        });

    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("0") @QueryParam("page") Integer page,
                           @DefaultValue("20") @QueryParam("per_page") Integer per_page,
                           @DefaultValue("") @QueryParam("query") String query) {

        return ExceptionHandler.handleException(() -> {
            HhEmployersPageDto empls = hhEmployerService.getAll(page, per_page, query);
            return Response.ok(empls).build();
        });
    }
}

