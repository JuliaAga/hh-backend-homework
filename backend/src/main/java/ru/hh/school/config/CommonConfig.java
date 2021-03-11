package ru.hh.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.hh.nab.common.properties.FileSettings;
import ru.hh.nab.datasource.DataSourceFactory;
import ru.hh.nab.datasource.DataSourceType;
import ru.hh.nab.hibernate.MappingConfig;
import ru.hh.nab.hibernate.NabHibernateCommonConfig;
import ru.hh.nab.starter.NabCommonConfig;
import ru.hh.school.dao.EmployerDao;
import ru.hh.school.dao.GenericDao;
import ru.hh.school.dao.VacancyDao;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({
        NabCommonConfig.class,
        NabHibernateCommonConfig.class,
        EmployerDao.class,
        GenericDao.class

})
public class CommonConfig {

    @Bean
    public MappingConfig mappingConfig() {
        MappingConfig mappingConfig = new MappingConfig();
        mappingConfig.addPackagesToScan("ru.hh.school.entity");
        return mappingConfig;
    }

    @Bean
    public DataSource dataSource(DataSourceFactory dataSourceFactory, FileSettings fileSettings) {
        return dataSourceFactory.create("master", false, fileSettings);
    }
}
