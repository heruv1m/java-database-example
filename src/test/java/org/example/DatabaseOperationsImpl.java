package org.example;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseOperationsImpl implements DatabaseOperations {
    private DataSource dataSource;

    @Override
    public void init(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public String getPersonNameByIdAndType(int id, int type) throws SQLException {
        try (var con = dataSource.getConnection();) {
            var stm = con.prepareStatement("select name from person where id=? and type=?");
            stm.setInt(1, id);
            stm.setInt(2, type);
            try (var resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
                throw new IllegalStateException("no person with parameters id:" + id + " type:" + type);
            }
        }
    }

    @Override
    public boolean updateName(int id, String name) throws SQLException {
        try (var con = dataSource.getConnection();) {
            var stm = con.prepareStatement("update person set name = ? where id=?");
            stm.setString(1, name);
            stm.setInt(2, id);
            return stm.executeUpdate() == 1;
        }
    }

    @Override
    public Person getPerson(int id) throws SQLException {
        try (var con = dataSource.getConnection();) {
            var stm = con.prepareStatement("select id, name, type from person where id=?");
            stm.setInt(1, id);
            try (var resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    Person person = new Person();
                    person.setId(id);
                    person.setName(resultSet.getString("name"));
                    person.setType(resultSet.getInt(("type")));

                    return person;
                }
                throw new IllegalStateException("no person with parameters id:" + id);
            }
        }
    }
}
