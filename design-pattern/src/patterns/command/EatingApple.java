package patterns.command;

public class EatingApple implements Command {
    Eating eating;

    public EatingApple(Eating eating) {
        this.eating = eating;
    }

    @Override
    public void executeCommand() {
        eating.eating();
        System.out.println("Apple");
    }
}
