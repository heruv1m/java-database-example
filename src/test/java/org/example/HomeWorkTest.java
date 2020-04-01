package org.example;

import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.junit.Assert.*;


public class HomeWorkTest {
    private DatabaseOperations databaseOperations;
    private DataSource dataSource;

    @Before
    public void init() throws SQLException {
        this.dataSource = DataSourceUtil.getDataSource();
        fillTable();
        this.databaseOperations = new DatabaseOperationsImpl();
        databaseOperations.init(dataSource);
    }

    @Test
    public void testGetPersonNameByIdAndType() throws SQLException {
        String name = databaseOperations.getPersonNameByIdAndType(1, 1);
        assertEquals("vasya", name);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetPersonNameFail() throws SQLException {
        databaseOperations.getPersonNameByIdAndType(999, 1);
    }

    @Test
    public void testUpdateName() throws SQLException {
        boolean result = databaseOperations.updateName(1, "newName");
        assertTrue(result);
        //todo проверить что в базенке
    }

    @Test
    public void testNotUpdatedName() throws SQLException {
        boolean result = databaseOperations.updateName(999, "newNameFail");
        assertFalse(result);
    }

    @Test
    public void testGetPerson() throws SQLException {
        Person reference = new Person().setId(2).setName("testGetPerson").setType(1);
        Person person = databaseOperations.getPerson(2);
        assertEquals(reference, person);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetPersonNotFound() throws SQLException {
        databaseOperations.getPerson(999);
    }


    private void fillTable() throws SQLException {
        try (var con = dataSource.getConnection();
             var stm = con.createStatement()
        ) {
            stm.execute("DROP ALL OBJECTS;");
            stm.execute("CREATE TABLE person(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), type INT);");
            stm.execute("insert into person (name, type) values ('vasya', 1)");
            stm.execute("insert into person (name, type) values ('testGetPerson', 1)");
        }
    }
}
