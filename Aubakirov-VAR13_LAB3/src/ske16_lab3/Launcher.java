package ske16_lab3;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            MachineDAO machineDAO = (MachineDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице клиентов 

            machineDAO.deleteAll(); // Удаление

            Machine name = new Machine("Bekko", "Черная"); // Создание
            machineDAO.insert(name);

            machineDAO.insert(new Machine("Bekko", "Серая")); // Добавление
            machineDAO.insert(new Machine("LG", "Белая"));
            
            /////////////////////
            machineDAO.append("Indesit");
            /////////////////////
            
            machineDAO.deleteByColorMachine("Бел"); // Удаление
            machineDAO.delete("Bekko", "Белая");
            
            //////////////////////
            machineDAO.delete("Indesit");
            /////////////////////
            
            List<Machine> names = machineDAO.findByNameMachine("ак");
            System.out.println(names != null ? names : "Нет данных");

            machineDAO.append("Bekko", "Красная");
            machineDAO.append("LG", "Белая");
            machineDAO.append("LG", "Коричневая");
            machineDAO.append("Samsung", "Голубая");

            machineDAO.update("Черная", "Красная");

            System.out.println("Магазин технодом предлагает вам качественные стиральные машины:");

            List<Machine> list = machineDAO.selectAll();
            for (Machine myMachine : list) {
                System.out.println(myMachine.getNameMachine() + " " + myMachine.getColorMachine());
            }

            System.out.println();
            System.out.println("Вывод записей с Bekko и Красная:");

            list = machineDAO.select("Bekko", "Красная");
            for (Machine myMachine : list) {
                System.out.println(myMachine.getNameMachine() + " " + myMachine.getColorMachine());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }

}
