package ru.gothmog.jaxb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gothmog.jaxb.dao.MrDao;
import ru.gothmog.jaxb.dao.impl.MrDaoImpl;

@Configuration
public class AppConfig {

    @Bean
    public MrDao mrDao(){
        return new MrDaoImpl();
    }
}
