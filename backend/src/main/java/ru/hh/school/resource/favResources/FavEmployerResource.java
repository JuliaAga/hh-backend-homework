package ru.hh.school.resource.favResources;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.server.ResponseStatusException;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.service.EmployerService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Controller
@Path("/favorites")
public class FavEmployerResource {
    private final EmployerService employerService;

    public FavEmployerResource(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GET
    @Path("/employer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") @DefaultValue("0") Integer page,
                           @QueryParam("per_page") @DefaultValue("20") Integer per_page) {
        return Response.ok(employerService.getAll(page, per_page)).build();
    }

    @POST
    @Path("/employer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(EmployerRequestDto dto) {
        try {
            return Response.ok(employerService.save(dto)).build();
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }

    @PUT
    @Path("/employer/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setComment(@PathParam("id") Integer id, String comment) {
        try {
            employerService.setComment(id, comment);
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
            employerService.refresh(id);
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
        try {
            employerService.delete(id);
            return Response.ok().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }



}