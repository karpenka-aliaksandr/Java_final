package Models.Menu;
import Commands.Base.Command;

public class PunktMenu {
    private String text;
    private Command command;
    public PunktMenu(String text, Command command) {
        this.text = text;
        this.command = command;
    }
    @Override
    public String toString() {
        return this.text;
    }
    public void run(){
        this.command.execute();
    }
    public Command getCommand(){
        return this.command;
    }
    
}
