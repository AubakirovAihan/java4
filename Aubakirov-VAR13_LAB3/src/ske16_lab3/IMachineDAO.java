package ske16_lab3;

import java.util.List;
import javax.sql.DataSource;

public interface IMachineDAO {
    void setDataSource(DataSource ds); // Установка связи с данныими
    void insert(Machine customer); // Вставка новой записи
    void append(String nameMachine, String colorMachine); // Добавление новой записи
    void append(String nameMachine);
    void deleteByColorMachine(String colorMachine); // Удаление записи по фамилии
    void delete(String nameMachine, String colorMachine); // Удаление записи с указанными названием и цветом
    void delete(String nameMachine);
    void deleteAll(); // Удаление всех запией
    void update(String oldcolorMachine, String newcolorMachine); // Изменение записей в таблице
    List<Machine> findByNameMachine(String nameMachine); // Получение записей с заданным названием 
    List<Machine> select(String nameMachine, String colorMachine); // Получение записей с заданными названием и цветом
    List<Machine> selectAll(); // Получение всех записей
}
