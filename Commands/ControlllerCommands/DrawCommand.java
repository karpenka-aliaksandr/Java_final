package Commands.ControlllerCommands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;
import Models.Menu.Menu;
import Models.Toys.Toy;
import Models.Toys.Toys;
import Views.Screen;
import Views.View;

public class DrawCommand extends ControllerCommand {

    public DrawCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        Screen screen = controller.getScreen();
        View view = screen.getView();
        Menu menu = screen.getMenu();
        Toys toys = controller.getToys();
        screen.setMenu(new Menu());
        Integer drawCount = 0;
        for (Toy toy : toys.getToysToArrayList()) {
            if (toy.getFrequency() > 0)
                drawCount = drawCount + toy.getCount();
        }
        String prompt = "Может быть разыграно игрушек: " + drawCount + ".\nВведите количество игрушек к розыгрышу (0 - не разыгрывать): ";
        screen.setBar(prompt);
        Integer dC;
        while (true) {
            dC = -1;
            String s_dC = view.getString();
            try {
                dC = Integer.valueOf(s_dC);   
            }catch (NumberFormatException e) {  
                screen.setBar("Неправильный формат числа!\n"+ prompt);  
                continue;
            } 
            if (dC>=0 && dC <= drawCount) {
                break;
            } else {
                screen.setBar("Не может быть разыграно " + dC + " игрушек." + "\n" + prompt);  
                continue;
            }
        }
        ArrayList<Toy> drawResult = new ArrayList<>(); 
        for (int i = 0; i < dC; i++) {
            ArrayList<Integer> drawToys = new ArrayList<>();
            for (Toy toy : toys.getToysToArrayList()) {
                if (toy.getCount()>0) {
                    for (int j = 0; j < toy.getFrequency(); j++) {
                        drawToys.add(toy.getId());
                    }
                }
            }
            int draw = (int) (Math.random()*drawToys.size());
            Integer res = drawToys.get(draw);
            for (Toy toy : toys.getToysToArrayList()) {
                if (toy.getId() == res) {
                    drawResult.add(toy);
                    toy.setCount(toy.getCount()-1);
                }
            }
        }

        String fileName = "draw.txt";
        try {
            File file = new File("./InOutFiles", fileName);
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file,true), "utf-8");
            BufferedWriter fw = new BufferedWriter(osw);
            for (Toy Toy : drawResult) {
                fw.write(Toy.getId().toString()+"\n");
                fw.write(Toy.getName()+"\n");
                fw.write(Toy.getCount().toString()+"\n");
                fw.write(Toy.getFrequency().toString()+"\n");
            }
            fw.flush();
            fw.close();
            screen.setBar("Результаты розыгрыша успешно сохранены.\n\nВведите пункт меню:");
        } catch (Exception e) {
            screen.setBar("Не удалось сохранить результаты розыгрыша.\n\nВведите пункт меню:");
            e.printStackTrace();
        }
        screen.setData(controller.getToys().toString());
        screen.setMenu(menu);
    }

}
