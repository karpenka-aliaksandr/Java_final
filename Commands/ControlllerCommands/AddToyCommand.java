package Commands.ControlllerCommands;

import java.util.Collections;

import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;
import Models.Menu.Menu;
import Models.Toys.Toy;
import Models.Toys.Toys;
import Views.Screen;
import Views.View;

public class AddToyCommand extends ControllerCommand {

    public AddToyCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        Screen screen = controller.getScreen();
        View view = screen.getView();
        Menu menu = screen.getMenu();
        Toys toys = controller.getToys();
        screen.setMenu(new Menu());
        screen.setBar("Введите id игрушки (0 - назначится автоматически): ");
        Integer id;
        while (true) {
            id = 0;
            String s_id = view.getString();
            try {
                id = Integer.valueOf(s_id);   
            }catch (NumberFormatException e) {  
                screen.setBar("Неправильный формат числа!\nВведите id игрушки (0 - назначится автоматически): ");  
                continue;
            } 
            if (toys.getIdsToArrayList().contains(id)) {
                screen.setBar("id = " + id.toString() + " уже существует, a должен быть уникальным!\nВведите id игрушки (0 - назначится автоматически): ");  
                continue;
            }
            if (id < 0) {
                screen.setBar("id = " + id.toString() + ", а должен быть положительным!\nВведите id игрушки (0 - назначится автоматически): ");  
                continue;
            }
            if (id == 0) {
                if (toys.getIdsToArrayList().isEmpty()) 
                id =1;
                else 
                id = Collections.max(toys.getIdsToArrayList()) + 1;
            }
            break;
        }
        screen.setBar("id: "+ id.toString()+".\nВведите наименование игрушки: ");
        String name = view.getString();
        screen.setBar("id: "+ id.toString()+". Наименование: " + name + ".\nВведите количество: ");
        Integer count;
        while (true) {
            count = 0;
            String s_count = view.getString();
            try {
                count = Integer.valueOf(s_count);   
            }catch (NumberFormatException e) {  
                screen.setBar("Неправильный формат числа!\nid: "+ id.toString()+". Наименование: " + name + ".\nВведите количество: ");  
                continue;
            } 
            if (count < 0) {
                screen.setBar("count = " + count.toString() + " должен быть положительным!\nid: "+ id.toString()+". Наименование: " + name + ".\nВведите количество: ");  
                continue;
            }
            break;
        }

        screen.setBar("id: "+ id.toString()+". Наименование: " + name + ". Количество: " + count.toString() + ".\nВведите вероятность выпадения в розыгрыше (0-100): ");
        Byte frequency;
        while (true) {
            frequency = 0;
            String s_frequency = view.getString();
            try {
                frequency = Byte.valueOf(s_frequency);   
            }catch (NumberFormatException e) {  
                screen.setBar("Неправильный формат числа!\nid: "+ id.toString()+". Наименование: " + name + ". Количество: " + count.toString() + ".\nВведите вероятность выпадения в розыгрыше (0-100): ");  
                continue;
            } 
            if (frequency < 0 || frequency > 100) {
                screen.setBar("frequency = " + frequency.toString() + ", а должна быть в пределах 0-100!\nid: "+ id.toString()+". Наименование: " + name + ". Количество: " + count.toString() + ".\nВведите вероятность выпадения в розыгрыше (0-100): ");  
                continue;
            }
            break;
        }

        toys.addToy(new Toy(id, name, count, frequency));
        screen.setData(controller.getToys().toString());
        screen.setMenu(menu);
        screen.setBar("Введите пункт меню:");
    }

}
