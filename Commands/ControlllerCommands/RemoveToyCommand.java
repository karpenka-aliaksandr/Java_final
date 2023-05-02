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
        String prompt = "Введите id игрушки, которую хотите удалить (-1 - удалить все, 0 - выйти без удаления): ";
        String punkt = "\nВведите пункт меню:";
        screen.setBar(prompt);
        Integer id;
        while (true) {
            id = 0;
            String s_id = view.getString();
            try {
                id = Integer.valueOf(s_id);   
            }catch (NumberFormatException e) {  
                screen.setBar("Неправильный формат числа!\n" + prompt);  
                continue;
            } 
            if (id == 0 ) {
                screen.setBar("Ничего не удалено.\n" + punkt);
                break;
            }
            if (id == -1 ) {
                toys.removeAllToy();
                screen.setBar("Удалены все записи.\n" + punkt);
                break;
            }

            if (toys.getIdsToArrayList().contains(id)) {
                if (toys.removeToy(id))
                    screen.setBar("Удалена запись с id = " + id + ".\n" + punkt);
                else 
                    screen.setBar("Не удалена запись с id = " + id + ".\n" + punkt);
                break;
            } else {
                screen.setBar("Нет записи с id = " + id + ".\n" + prompt);  
                continue;
            }
        }
                
        screen.setData(controller.getToys().toString());
        screen.setMenu(menu);
    }

}
