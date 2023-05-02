package Commands.ControlllerCommands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;
import Models.Menu.Menu;
import Models.Toys.Toy;
import Models.Toys.Toys;
import Views.Screen;
import Views.View;

public class DistributionCommand extends ControllerCommand {

    public DistributionCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        Screen screen = controller.getScreen();
        View view = screen.getView();
        Menu menu = screen.getMenu();
        Toys toys = new Toys();
        screen.setData("");
        screen.setMenu(new Menu());

        String fileName = "draw.txt";
        try {
            File file = new File("./InOutFiles", fileName);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "utf-8");
            BufferedReader reader = new BufferedReader(isr);
            String s_id = reader.readLine();
            while (s_id != null) {
                Integer id = Integer.valueOf(s_id);
                String name = reader.readLine();
                Integer count = Integer.valueOf(reader.readLine());
                Byte frequency = Byte.valueOf(reader.readLine());
                toys.addToy(new Toy(id, name, count, frequency));
                s_id = reader.readLine();
            }
            reader.close();
            isr.close();
        } catch (Exception e) {
            screen.setBar("Не удалось загрузить результаты розыгрышей (файл поврежден или не найден).\n\nВведите пункт меню:");
            e.printStackTrace();
        }
        Integer distributionCount = toys.getCount();
        StringBuilder str = new StringBuilder();
        if (! toys.getToysToArrayList().isEmpty())
            str.append("В списке ожидания выдачи игрушек: " + distributionCount + "\n");
        for (Toy toy : toys.getToysToArrayList()) {
            str.append(toy.getName() + "  ");
        }
        str.append("\n\nВведите количество игрушек к выдаче (0 - ничего не выдавать):  ");
        String prompt = str.toString();
        screen.setBar(prompt);
        Integer dist;
        while (true) {
            dist = -1;
            String s_dist = view.getString();
            try {
                dist = Integer.valueOf(s_dist);   
            }catch (NumberFormatException e) {  
                screen.setBar("Неправильный формат числа!\n"+ prompt);  
                continue;
            } 
            if (dist>=0 && dist <= distributionCount) {
                break;
            } else {
                screen.setBar("Не может быть выдано " + dist + " игрушек." + "\n" + prompt);  
                continue;
            }
        }
        System.out.println(dist);
        for (int i = 0; i < dist; i++) {
            toys.removeIndex(0);
            System.out.println(toys.getCount());
        }

        try {
            File file = new File("./InOutFiles", fileName);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file,false), "utf-8");
            BufferedWriter fw = new BufferedWriter(osw);
            for (Toy Toy : toys.getToysToArrayList()) {
                fw.write(Toy.getId().toString()+"\n");
                fw.write(Toy.getName()+"\n");
                fw.write(Toy.getCount().toString()+"\n");
                fw.write(Toy.getFrequency().toString()+"\n");
            }
            fw.flush();
            fw.close();
            screen.setBar("Результаты выдачи успешно сохранены.\n\nВведите пункт меню:");
        } catch (Exception e) {
            screen.setBar("Не удалось сохранить результаты выдачи.\n\nВведите пункт меню:");
            e.printStackTrace();
        }
        screen.setData(controller.getToys().toString());
        screen.setMenu(menu);

    }
}