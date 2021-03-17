package ru.hh.school.resource.hhResources;


import org.springframework.stereotype.Controller;
import ru.hh.school.dto.hhDto.HhVacanciesPageDto;
import ru.hh.school.dto.hhDto.HhVacancyDto;
import ru.hh.school.exception.ExceptionHandler;
import ru.hh.school.service.hhServices.HhVacancyService;


import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class HhVacancyResourse {

    HhVacancyService hhVacancyService;

    public HhVacancyResourse(HhVacancyService hhVacancyService) {
        this.hhVacancyService = hhVacancyService;
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@NotNull @PathParam("id") Integer id) {
        return ExceptionHandler.handleException(() -> {
            HhVacancyDto vac = hhVacancyService.get(id);
            return Response.ok(vac).build();
        });

    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("0") @QueryParam("page") Integer page,
                           @DefaultValue("20") @QueryParam("per_page") Integer per_page,
                           @DefaultValue("") @QueryParam("query") String query) {

        return ExceptionHandler.handleException(() -> {
            HhVacanciesPageDto vac = hhVacancyService.getAll(page, per_page, query);
            return Response.ok(vac).build();
        });
    }
}

