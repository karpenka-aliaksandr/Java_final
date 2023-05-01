import java.util.Scanner;

import Controllers.Controller;
import Models.Toys.Toys;
import Views.Screen;
import Views.View;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in,"Cp866");

        Toys toys = new Toys();
        View view = new View(scan);
        Screen screen = new Screen(view);
        Controller ctrl = new Controller(screen, toys);
        ctrl.run();
        scan.close();
    }
}
