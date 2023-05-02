package Commands.ControlllerCommands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;
import Models.Menu.Menu;
import Models.Toys.Toy;
import Models.Toys.Toys;
import Views.Screen;
import Views.View;

public class SaveToFileToysCommand extends ControllerCommand {

    public SaveToFileToysCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        Screen screen = controller.getScreen();
        View view = screen.getView();
        Menu menu = screen.getMenu();
        Toys Toys = controller.getToys();
        screen.setMenu(new Menu());
        screen.setBar("Введите имя файла: ");
        String fileName = view.getString()+".txt";

        try {
            File file = new File("./InOutFiles", fileName);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
            BufferedWriter fw = new BufferedWriter(osw);
            for (Toy Toy : Toys.getToysToArrayList()) {
                fw.write(Toy.getId().toString()+"\n");
                fw.write(Toy.getName()+"\n");
                fw.write(Toy.getCount().toString()+"\n");
                fw.write(Toy.getFrequency().toString()+"\n");
            }
            fw.flush();
            fw.close();
            screen.setBar("Витрина успешно cохранена.\n\nВведите пункт меню:");
        } catch (Exception e) {
            screen.setBar("Не удалось сохранить витрину.\n\nВведите пункт меню:");
            e.printStackTrace();
        }
        screen.setData(controller.getToys().toString());
        screen.setMenu(menu);
    }

}
