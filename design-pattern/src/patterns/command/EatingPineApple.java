package patterns.command;

public class EatingPineApple implements Command {
    Eating eating;

    public EatingPineApple(Eating eating) {
        this.eating = eating;
    }

    @Override
    public void executeCommand() {
        eating.eating();
        System.out.println("PineApple");
    }
}
