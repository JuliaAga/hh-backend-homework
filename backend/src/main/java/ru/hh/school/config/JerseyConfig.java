package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.resource.favResources.FavEmployerResource;
import ru.hh.school.resource.hhResources.EmployerResourse;
import ru.hh.school.resource.hhResources.VacancyResourse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Configuration
@Import({
        FavEmployerResource.class,
        EmployerResourse.class,
        VacancyResourse.class
})
public class JerseyConfig {
    @Bean
    public Client client() {
        return ClientBuilder.newClient();
    }
}
