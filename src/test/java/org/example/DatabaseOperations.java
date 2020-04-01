package org.example;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Скрипт создания таблицы
 * CREATE TABLE person(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), type INT);
 */
public interface DatabaseOperations {

    void init(DataSource dataSource);

    /**
     * Вернуть имя по id и type, бросить исключение IllegalStateException, если не найден
     */
    String getPersonNameByIdAndType(int id, int type) throws SQLException;

    /**
     * Обновить имя по id, вернуть true, если удалось, false если нет
     */
    boolean updateName(int id, String name) throws SQLException;

    /**
     * Вернуть объект Person по id, бросить исключение IllegalStateException, если не найден
     */
    Person getPerson(int id) throws SQLException;

}

