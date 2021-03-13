package ru.hh.school.resource.hhResources;


import org.springframework.stereotype.Controller;
import ru.hh.school.HhException;
import ru.hh.school.service.hhApi.HhVacancyService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/vacancy")
@Produces(MediaType.APPLICATION_JSON)
@Controller
public class VacancyResourse {

    HhVacancyService hhVacancyService;

    public VacancyResourse(HhVacancyService hhVacancyService) {
        this.hhVacancyService = hhVacancyService;
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Integer id) {
        try {
            return Response.ok(hhVacancyService.get(id)).build();
        } catch (HhException ex) {
            //TODO rewrite this
            return Response.status(Response.Status.fromStatusCode(Integer.parseInt(ex.getMessage()))).build();
        }

    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("page") Integer page,
                           @QueryParam("per_page") Integer per_page,
                           @QueryParam("query") String query) {
        try {
            return Response.ok(hhVacancyService.getAll(page, per_page, query)).build();
        } catch (HhException ex) {
            return Response.status(Response.Status.fromStatusCode(Integer.parseInt(ex.getMessage()))).build();
        }
    }
}

