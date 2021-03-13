package ru.hh.school.resource.favResources;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.server.ResponseStatusException;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.service.EmployerService;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    public List<Employer> getAll(@QueryParam("page") @DefaultValue("0") Integer page,
                                 @QueryParam("per_page") @DefaultValue("20") Integer per_page)
    {//TODO передалать ответ в пагинацию
        return employerService.getAll(page, per_page);

    }
    @POST
    @Path("/employer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Employer save(EmployerRequestDto dto) {
        try {
            return employerService.save(dto);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Operation failed", ex);
        }
    }

    //PUT /favorites/employer/{employer_id}

    //POST /favorites/employer/{employer_id}/refresh

    //DELETE /favorites/employer/{employer_id}



}