package ru.hh.school.resource.favResources;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.server.ResponseStatusException;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.exception.ExceptionHandler;
import ru.hh.school.service.favServices.FavEmployerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Controller
@Path("/favorites")
public class FavEmployerResource {
    private final FavEmployerService favEmployerService;

    public FavEmployerResource(FavEmployerService favEmployerService) {
        this.favEmployerService = favEmployerService;
    }

    @GET
    @Path("/employer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("0") Integer page,
                           @QueryParam("per_page") @DefaultValue("20") Integer per_page) {
        return Response.ok(favEmployerService.getAll(page, per_page)).build();
    }

    @POST
    @Path("/employer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(EmployerRequestDto dto) {
        return ExceptionHandler.handleException(() -> {
            Employer empl = favEmployerService.save(dto);
            return Response.ok(empl).build();
        });
    }

    @PUT
    @Path("/employer/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setComment(@PathParam("id") Integer id, String comment) {
        try {
            favEmployerService.setComment(id, comment);
            return Response.ok().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }

    @POST
    @Path("/employer/{id}/refresh")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response refresh(@PathParam("id") Integer id) {
        try {
            favEmployerService.refresh(id);
            return Response.ok().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }

    @DELETE
    @Path("/employer/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        return ExceptionHandler.handleException(() -> {
            favEmployerService.delete(id);
            return Response.ok().build();
        });
    }



}