package patterns.adapter;

public class Adapter implements OldTarget {
    private NewTarget newTarget;

    public Adapter(NewTarget newTarget) {
        this.newTarget = newTarget;
    }

    @Override
    public void request() {
        newTarget.request();
    }
}
