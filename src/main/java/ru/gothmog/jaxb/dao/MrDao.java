package ru.gothmog.jaxb.dao;


import ru.gothmog.jaxb.model.Mr;

import java.util.List;

public interface MrDao {

    List<Mr> getAllMr();

    List<Mr> getAllTargetMr();
}
