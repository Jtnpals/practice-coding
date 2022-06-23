package patterns.adapter;

public class OldAdaptee implements OldTarget {
    @Override
    public void request() {
        System.out.println("Old Adaptee");
    }
}
