package ru.hh.school.resource.favResources;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;
import ru.hh.school.exception.ExceptionHandler;
import ru.hh.school.exception.HhException;
import ru.hh.school.dto.request.VacancyRequestDto;
import ru.hh.school.service.favServices.FavVacancyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.Callable;

@Produces(MediaType.APPLICATION_JSON)
@Controller
@Path("/favorites")
public class FavVacancyResource {
    private final FavVacancyService favVacancyService;

    public FavVacancyResource(FavVacancyService favVacancyService) {
        this.favVacancyService = favVacancyService;
    }

    @GET
    @Path("/vacancy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("0") Integer page,
                           @QueryParam("per_page") @DefaultValue("20") Integer per_page) {
        return Response.ok(favVacancyService.getAll(page, per_page)).build();
    }

    @POST
    @Path("/vacancy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(VacancyRequestDto dto) {
        return ExceptionHandler.handleException(() -> {
            Vacancy vac = favVacancyService.save(dto);
            return Response.ok(vac).build();
        });
    }

    @PUT
    @Path("/vacancy/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setComment(@PathParam("id") Integer id, String comment) {
        try {
            favVacancyService.setComment(id, comment);
            return Response.ok().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }

    @POST
    @Path("/vacancy/{id}/refresh")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response refresh(@PathParam("id") Integer id) {
        try {
            favVacancyService.refresh(id);
            return Response.ok().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }

    @DELETE
    @Path("/vacancy/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        return ExceptionHandler.handleException(() -> {
            favVacancyService.delete(id);
            return Response.ok().build();
        });
    }
}
