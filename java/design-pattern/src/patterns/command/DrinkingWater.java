package patterns.command;

public class DrinkingWater implements Command {
    private Drinking drinking;

    public DrinkingWater(Drinking drinking) {
        this.drinking = drinking;
    }

    @Override
    public void executeCommand() {
        drinking.drinking();
        System.out.println("Water");
    }
}
