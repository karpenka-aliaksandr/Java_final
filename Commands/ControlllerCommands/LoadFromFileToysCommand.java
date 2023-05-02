package Commands.ControlllerCommands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;
import Models.Menu.Menu;
import Models.Toys.Toy;
import Views.Screen;
import Views.View;

public class LoadFromFileToysCommand extends ControllerCommand {

    public LoadFromFileToysCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        Screen screen = controller.getScreen();
        View view = screen.getView();
        Menu menu = screen.getMenu();
        screen.setMenu(new Menu());
        screen.setBar("Введите имя файла: ");
        String fileName = view.getString()+".txt";

        try {
            File file = new File("./InOutFiles", fileName);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader reader = new BufferedReader(isr);
            String s_id = reader.readLine();
            controller.getToys().removeAllToy();
            while (s_id != null) {
                Integer id = Integer.valueOf(s_id);
                String name = reader.readLine();
                Integer count = Integer.valueOf(reader.readLine());
                Byte frequency = Byte.valueOf(reader.readLine());
                controller.getToys()
                        .addToy(new Toy(id, name, count, frequency));
                s_id = reader.readLine();
            }
            reader.close();
            isr.close();
            screen.setBar("Витрина успешно загружена.\n\nВведите пункт меню:");
            
        } catch (Exception e) {
            screen.setBar("Не удалось загрузить витрину (файл поврежден или не найден).\n\nВведите пункт меню:");
            e.printStackTrace();
        }
        screen.setData(controller.getToys().toString());
        screen.setMenu(menu);
    }

}
