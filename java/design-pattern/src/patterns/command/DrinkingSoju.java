package patterns.command;

public class DrinkingSoju implements Command {
    Drinking drinking;

    public DrinkingSoju(Drinking drinking) {
        this.drinking = drinking;
    }

    @Override
    public void executeCommand() {
        drinking.drinking();
        System.out.println("Soju");
    }
}
