package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.resource.favResources.FavEmployerResource;
import ru.hh.school.resource.favResources.FavVacancyResource;
import ru.hh.school.resource.hhResources.HhEmployerResourse;
import ru.hh.school.resource.hhResources.HhVacancyResourse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@Configuration
@Import({
        FavEmployerResource.class,
        FavVacancyResource.class,
        HhEmployerResourse.class,
        HhVacancyResourse.class
})
public class JerseyConfig {
    @Bean
    public Client client() {
        return ClientBuilder.newClient();
    }
}
