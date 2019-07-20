package patterns.command;

public class Doing {
    Command command;

    public Doing(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void doing(){
        command.executeCommand();
    }
}
