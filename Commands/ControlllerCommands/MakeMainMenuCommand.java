package Commands.ControlllerCommands;
import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;
import Models.Menu.Menu;
import Models.Menu.PunktMenu;


public class MakeMainMenuCommand extends ControllerCommand {
    public MakeMainMenuCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        Menu menu = new Menu();
        menu.addPunkt(1,new PunktMenu("Загрузить игрушки (витрину магазина) из файла", new LoadFromFileToysCommand(controller)));
        menu.addPunkt(2,new PunktMenu("Сохранить игрушки (витрину) в файл", new SaveToFileToysCommand(controller)));
        menu.addPunkt(3,new PunktMenu("Добавить игрушку в витрину", new AddToyCommand(controller)));
        menu.addPunkt(4,new PunktMenu("Удалить игрушку из витрины", new RemoveToyCommand(controller)));
        menu.addPunkt(0,new PunktMenu("Выход", new ExitAppCommand(controller)));
        controller.getScreen().setMenu(menu);
        controller.getScreen().setBar("Введите пункт меню: ");
    }
}