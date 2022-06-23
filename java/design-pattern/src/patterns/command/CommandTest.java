package patterns.command;

public class CommandTest {
    public static void main(String[] args) {
        Eating eating = new Eating();
        Drinking drinking = new Drinking();

        Command command = new DrinkingSoju(drinking);
        Doing doing = new Doing(command);
        doing.doing();

        command = new EatingApple(eating);
        doing.setCommand(command);
        doing.doing();

    }
}
