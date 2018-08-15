package ru.gothmog.jaxb.dao.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.gothmog.jaxb.dao.MrDao;
import ru.gothmog.jaxb.model.Mr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.gothmog.jaxb.dao.CloseConnect.finalySQL;

public class MrDaoImpl implements MrDao {

    private static final Logger log = LoggerFactory.getLogger(MrDaoImpl.class);

    @Autowired
    @Qualifier("jdbcTemplateBatch")
    private JdbcTemplate jdbcTemplateBatch;

    @Autowired
    @Qualifier("jdbcTemplateBase")
    public JdbcTemplate jdbcTemplateBase;

    @Override
    public List<Mr> getAllMr() {
        log.info("Get all mrs");
        List<Mr> mrList = new ArrayList<>();
        String sql = "select ao_name,mr_name,okmo_code,ao_id from gisrenova.mr";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = jdbcTemplateBatch.getDataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Mr mr = new Mr();
                mr.setAoName(resultSet.getString("ao_name"));
                mr.setMrName(resultSet.getString("mr_name"));
                mr.setOkmoCode(resultSet.getString("okmo_code"));
                mr.setAoId(resultSet.getInt("ao_id"));
                mrList.add(mr);
            }
        } catch (SQLException ex) {
            log.error("Error when getting all aos", ex);
        } finally {
            finalySQL(connection, preparedStatement, resultSet, log);
        }
        return mrList;
    }

    @Override
    public List<Mr> getAllTargetMr() {
        log.info("Get all mrs target");
        List<Mr> mrList = new ArrayList<>();
        Mr mr = null;
        String sql = "select ao_name,name_ter from pptegko.mr";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = jdbcTemplateBase.getDataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mr = new Mr();
                mr.setAoName(resultSet.getString("ao_name"));
                mr.setMrName(resultSet.getString("name_ter"));
//                mr.setOkmoCode(resultSet.getString("okmo_code"));
//                mr.setAoId(resultSet.getInt("ao_id"));
                mrList.add(mr);
            }
        } catch (SQLException ex) {
            log.error("Error when getting all aos", ex);
        } finally {
            finalySQL(connection, preparedStatement, resultSet, log);
        }
        return mrList;
    }


}
