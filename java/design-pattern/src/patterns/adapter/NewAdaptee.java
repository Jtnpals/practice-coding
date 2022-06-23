package patterns.adapter;

public class NewAdaptee implements NewTarget {

    @Override
    public void request() {
        System.out.println("New Adaptee");
    }
}
