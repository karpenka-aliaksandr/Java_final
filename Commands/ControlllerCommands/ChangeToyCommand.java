package Commands.ControlllerCommands;

import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;
import Models.Menu.Menu;
import Models.Toys.Toy;
import Models.Toys.Toys;
import Views.Screen;
import Views.View;

public class ChangeToyCommand extends ControllerCommand {

    public ChangeToyCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        Screen screen = controller.getScreen();
        View view = screen.getView();
        Menu menu = screen.getMenu();
        Toys toys = controller.getToys();
        Toy toy;
        boolean changed = false;
        screen.setMenu(new Menu());
        String prompt = "Введите id игрушки, которую хотите изменить: (0 - выйти без изменения): ";
        screen.setBar(prompt);
        Integer id;
        while (true) {
            id = -1;
            String s_id = view.getString();
            try {
                id = Integer.valueOf(s_id);   
            }catch (NumberFormatException e) {  
                screen.setBar("Неправильный формат числа!\n"+ prompt);  
                continue;
            } 
            if (toys.getIdsToArrayList().contains(id) || id == 0) {
                break;
            } else {
                screen.setBar("Нет записи с id = " + id.toString() + "\n" + prompt);  
                continue;
            }
        }
        if (id == 0) {
            // screen.setBar("Запись не изменена.\n\nВведите пункт меню:");
        } else {
            toy = toys.getToy(id);
            screen.setBar("id: "+ id.toString()+ ". Наименование:  " + toy.getName() + 
                ".\nВведите новое наименование игрушки ('-' - оставить предыдущее): ");
            String name = view.getString();
            if (! name.equals("-")) {
                changed = true;
                toy.setName(name);
            }
            screen.setBar("id: "+ id.toString()+". Наименование: " + toy.getName() + ". Количество: " + toy.getCount().toString() + 
                ".\nВведите новое количество ('-' - оставить предыдущее): ");
            Integer count;
            while (true) {
                count = -1;
                String s_count = view.getString();
                if (! s_count.equals("-")) {
                    try {
                        count = Integer.valueOf(s_count);   
                    } catch (NumberFormatException e) {  
                        screen.setBar("Неправильный формат числа!" + 
                        "\nid: " + id.toString()+". Наименование: " + toy.getName() + ". Количество: " + toy.getCount().toString() + 
                        ".\nВведите новое количество (пустая строка - оставить предыдущее): ");  
                        continue;
                    } 
                    if (count < 0) {
                        screen.setBar("count = " + count.toString() + " должен быть положительным!" + 
                        "\nid: "+ id.toString()+". Наименование: " + toy.getName() + ". Количество: " + toy.getCount().toString() + 
                        ".\nВведите новое количество (пустая строка - оставить предыдущее): ");  
                        continue;
                    } else {
                        toy.setCount(count);
                        changed = true;
                        break;
                    }
                } else 
                    break;
            } 
            screen.setBar("id: "+ id.toString()+". Наименование: " + toy.getName() + ". Количество: " + toy.getCount().toString() + ". Вероятность выпадения в розыгрыше: " + toy.getFrequency().toString() +
            ".\nВведите новую вероятность от 0 до 100 ('-' - оставить предыдущее): ");
            Byte frequency;
            while (true) {
                frequency = -1;
                String s_frequency = view.getString();
                if (! s_frequency.equals("-")) {
                    try {
                        frequency = Byte.valueOf(s_frequency);   
                    }catch (NumberFormatException e) {  
                        screen.setBar("Неправильный формат числа!" + 
                        "\nid: "+ id.toString()+". Наименование: " + toy.getName() + ". Количество: " + toy.getCount().toString() + ". Вероятность выпадения в розыгрыше: " + toy.getFrequency().toString() +
                        ".\nВведите новую вероятность от 0 до 100 (пустая строка - оставить предыдущее): ");  
                        continue;
                    } 
                    if (frequency < 0 || frequency > 100) {
                        screen.setBar("frequency = " + frequency.toString() + " должен быть в пределах 0-100!" + 
                        "\nid: "+ id.toString()+". Наименование: " + toy.getName() + ". Количество: " + toy.getCount().toString() + ". Вероятность выпадения в розыгрыше: " + toy.getFrequency().toString() +
                        ".\nВведите новую вероятность от 0 до 100 (пустая строка - оставить предыдущее): ");  
                        continue;
                    } else {
                        toy.setFrequency(frequency);
                        changed = true;
                        break;
                    }
                } else 
                    // screen.setBar("Введите пункт меню: ");
                    break;    
            }
        }
        
        screen.setData(controller.getToys().toString());
        screen.setMenu(menu);
        if (changed) screen.setBar("Запись изменена.\n\nВведите пункт меню:");
        else screen.setBar("Запись не изменена.\n\nВведите пункт меню: ");
    }
}
