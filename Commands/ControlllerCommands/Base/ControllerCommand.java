package Commands.ControlllerCommands.Base;

import Controllers.Controller;

import Commands.Base.BaseCommand;

public abstract class ControllerCommand extends BaseCommand{
    protected Controller controller;

    public ControllerCommand(Controller controller) {
        this.controller = controller;
    }
    
}
