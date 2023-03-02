package ske16_lab3;

public class Machine {
    int id;            // Код записи
    String nameMachine;  // Название 
    String colorMachine;   // Цвет 

    public Machine() {
        this.id = 0;
        this.nameMachine = "";
        this.colorMachine = "";
    }

    public Machine(String nameMachine, String colorMachine) {
        this.id = 0;
        this.nameMachine = nameMachine;
        this.colorMachine = colorMachine;
    }

    public Machine(String nameMachine) {
        this.id = 0;
        this.nameMachine = nameMachine;
        
    }

    public int getId() {
        return id;
    }

    public String getNameMachine() {
        return nameMachine;
    }

    public String getColorMachine() {
        return colorMachine;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNameMachine(String nameMachine) {
        this.nameMachine = nameMachine;
    }

    public void setColorMachine(String colorMachine) {
        this.colorMachine = colorMachine;
    }


    @Override
    public String toString() {
        return String.format("Фирма стиральной машины - %s, Цвет - %s", nameMachine, colorMachine);
    }
}
