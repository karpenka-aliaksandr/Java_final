package Commands.ControlllerCommands;

import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;
import Models.Menu.Menu;
import Models.Toys.Toys;
import Views.Screen;
import Views.View;

public class RemoveToyCommand extends ControllerCommand {

    public RemoveToyCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        Screen screen = controller.getScreen();
        View view = screen.getView();
        Menu menu = screen.getMenu();
        Toys toys = controller.getToys();
        screen.setMenu(new Menu());
        screen.setBar("Введите id игрушки, которую хотите удалить (-1 - удалить все, 0 - выйти без удаления): ");
        Integer id;
        while (true) {
            id = 0;
            String s_id = view.getString();
            try {
                id = Integer.valueOf(s_id);   
            }catch (NumberFormatException e) {  
                screen.setBar("Неправильный формат числа!\nВведите id игрушки, которую хотите удалить (-1 - удалить все, 0 - выйти без удаления): ");  
                continue;
            } 
            if (id == 0 ) {
                screen.setBar("Ничего не удалено.\n\nВведите пункт меню:");
                break;
            }
            if (id == -1 ) {
                toys.removeAllToy();
                screen.setBar("Удалены все записи.\n\nВведите пункт меню:");
                break;
            }

            if (toys.getIdsToArrayList().contains(id)) {
                if (toys.removeToy(id))
                    screen.setBar("Удалена запись с id = " + id + ".\n\nВведите пункт меню:");
                else 
                    screen.setBar("Не удалена запись с id = " + id + ".\n\nВведите пункт меню:");
                break;
            } else {
                screen.setBar("Нет записи с id = " + id + ".\nВведите id игрушки, которую хотите удалить (-1 - удалить все, 0 - выйти без удаления): ");  
                continue;
            }
        }
                
        screen.setData(controller.getToys().toString());
        screen.setMenu(menu);
    }

}
