package ske16_lab3;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class MachineDAO implements IMachineDAO {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Machine customer) { // Реализация вставки новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO ske16_lab3 (NAME_MACHINE, COLOR_MACHINE) VALUES(?,?)",
                new Object[]{customer.getNameMachine(), customer.getColorMachine()});
    }

    @Override
    public void append(String nameMachine, String colorMachine) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO ske16_lab3 (NAME_MACHINE, COLOR_MACHINE) VALUES(?,?)", new Object[]{nameMachine, colorMachine});
    }

   
    @Override
    public void deleteByColorMachine(String colorMachine) {  // Реализация удаления записей по цвету
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM ske16_lab3 WHERE COLOR_MACHINE LIKE ?", new Object[]{'%' + colorMachine + '%'});
    }

    @Override
    public void delete(final String nameMachine, final String colorMachine) {  // Реализация удаления записей с указанными названием и цветом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from ske16_lab3 where NAME_MACHINE = ? AND COLOR_MACHINE = ?", new Object[]{nameMachine, colorMachine});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override

    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE from ske16_lab3");
    }

    @Override
    public void update(String oldColorMachine, String newColorMachine) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE ske16_lab3 SET COLOR_MACHINE = ? WHERE COLOR_MACHINE = ?", new Object[]{newColorMachine, oldColorMachine});
    }

    @Override
    public List<Machine> findByNameMachine(String nameMachine) {  // Реализация поиска записей по названию
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM ske16_lab3 WHERE NAME_MACHINE LIKE ?";
        List<Machine> machine = select.query(sql, new Object[]{'%' + nameMachine + '%'}, new MachineRowMapper());
        return machine;
    }

    @Override
    public List<Machine> select(String nameMachine, String colorMachine) {  // Реализация получения записей с заданными названием и цветом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select  * from ske16_lab3 where NAME_MACHINE = ? AND COLOR_MACHINE= ?",
                new Object[]{nameMachine, colorMachine}, new MachineRowMapper());
    }

    @Override
    public List<Machine> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("select * from ske16_lab3", new MachineRowMapper());
    }

    @Override
    public void append (String nameMachine) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        String colorMachine = "Зеленая";
        insert.update("INSERT INTO ske16_lab3(NAME_MACHINE, COLOR_MACHINE) VALUES(?,?)", new Object[]{nameMachine, colorMachine});
    }
    
   @Override
    public void delete(final String nameMachine) {  // Реализация удаления записей с указанными названием и цветом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE from ske16_lab3 where NAME_MACHINE = ?", new Object[]{nameMachine});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }
}