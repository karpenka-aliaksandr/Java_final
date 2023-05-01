package Controllers;

import Commands.ControlllerCommands.MakeMainMenuCommand;
import Models.Menu.PunktMenu;
import Models.Toys.Toys;
import Views.View;
import Views.Screen;

public class Controller {
    boolean exitApp;
    Screen screen;
    Toys toys;
    View view;

    public Controller(Screen screen, Toys toys) {
        this.screen = screen;
        this.view = screen.getView();
        this.toys = toys;
        this.exitApp = false;
    }

    public void run() {

        new MakeMainMenuCommand(this).execute();
        while (!exitApp) {
            screen.show();
            int numPunkt = view.getPositiveInt();
            PunktMenu pm = screen.getMenu().getPunkt(numPunkt);
            if (pm == null) {
                screen.setBar("Неправильный выбор");
            } else {
                pm.run();
            }
        }

    }

    public Screen getScreen() {
        return this.screen;
    }

    public Toys getToys() {
        return this.toys;
    }

    public void setExitApp() {
        this.exitApp = true;
    }

}
