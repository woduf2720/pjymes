package com.example.pjymes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@SpringBootTest
class PjymesApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select now()");
                ResultSet resultSet = preparedStatement.executeQuery();
        ){
            resultSet.next();

            System.out.println(resultSet.getString(1));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
