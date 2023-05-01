package Commands.ControlllerCommands;
import Commands.ControlllerCommands.Base.ControllerCommand;
import Controllers.Controller;

public class ExitAppCommand extends ControllerCommand{
 
    public ExitAppCommand(Controller controller) {
        super(controller);
    }

    @Override
    public void execute() {
        controller.getScreen().setBar("До свидания.");
        controller.setExitApp();
    }

}
