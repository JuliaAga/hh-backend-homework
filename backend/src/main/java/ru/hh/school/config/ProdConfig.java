package ru.hh.school.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.hibernate.NabHibernateProdConfig;
import ru.hh.nab.starter.NabProdConfig;


@Configuration
@Import({NabHibernateProdConfig.class, NabProdConfig.class, CommonConfig.class})
@ComponentScan("ru.hh.school")
public class ProdConfig {

}
