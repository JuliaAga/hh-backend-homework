package ru.hh.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.school.resource.EmployerResource;
import ru.hh.school.resource.ExampleResource;
import ru.hh.school.resource.OuterEmployerResourse;

@Configuration
@Import({
        ExampleResource.class,
        EmployerResource.class,
        OuterEmployerResourse.class
})
public class JerseyConfig {
}
