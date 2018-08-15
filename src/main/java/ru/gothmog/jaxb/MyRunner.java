package ru.gothmog.jaxb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.gothmog.jaxb.dao.MrDao;
import ru.gothmog.jaxb.model.Mr;

import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    private MrDao mrDao;

    @Override
    public void run(String... args) throws Exception {
        List<Mr> mrList = mrDao.getAllMr();
        for (Mr mr: mrList){
            System.out.println("datasource : " + mr);
        }
        List<Mr> mrListTarget = mrDao.getAllTargetMr();
        for (Mr mr : mrListTarget){
            System.out.println("target_datasource : " + mr);
        }
    }
}
