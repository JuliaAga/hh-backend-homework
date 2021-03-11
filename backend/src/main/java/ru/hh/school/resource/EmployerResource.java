package ru.hh.school.resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import ru.hh.school.dto.request.EmployerRequestDto;
import ru.hh.school.entity.Employer;
import ru.hh.school.service.EmployerService;


import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Controller
@Path("/fav")
public class EmployerResource {
    private final EmployerService employerService;

    public EmployerResource(EmployerService employerService) {
        this.employerService = employerService;
    }

    @RequestMapping("/employer")
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAll(@RequestParam Integer page, @RequestParam Integer per_page) {
      /*  if (page == null) page = 0;
        if (page == null) per_page = 20;*/
       // return employerService.getAll(page, per_page);
        return ";jgfg";
    }
    @PostMapping("/employer")
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