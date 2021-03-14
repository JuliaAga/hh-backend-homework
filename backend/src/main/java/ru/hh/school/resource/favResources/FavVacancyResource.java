package ru.hh.school.resource.favResources;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;
import ru.hh.school.dto.request.VacancyRequestDto;
import ru.hh.school.service.VacancyService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Controller
@Path("/favorites")
public class FavVacancyResource {
    private final VacancyService vacancyService;

    public FavVacancyResource(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GET
    @Path("/vacancy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("0") Integer page,
                           @QueryParam("per_page") @DefaultValue("20") Integer per_page) {
        return Response.ok(vacancyService.getAll(page, per_page)).build();
    }

    @POST
    @Path("/vacancy")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(VacancyRequestDto dto) {
        try {
            return Response.ok(vacancyService.save(dto)).build();
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }

    @PUT
    @Path("/vacancy/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setComment(@PathParam("id") Integer id, String comment) {
        try {
            vacancyService.setComment(id, comment);
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
            vacancyService.refresh(id);
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
        try {
            vacancyService.delete(id);
            return Response.ok().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }



}