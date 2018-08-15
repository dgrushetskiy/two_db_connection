package ru.gothmog.jaxb.dao;

import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CloseConnect {

    public static void finalySQL(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet, Logger log) {
        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            log.error("Error close rs, ps, connect", ex);
        }
    }
}
