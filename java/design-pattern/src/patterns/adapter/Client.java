package patterns.adapter;

public class Client {
    private OldTarget oldTarget;

    public Client(OldTarget oldTarget) {
        this.oldTarget = oldTarget;
    }

    public void print(){
        oldTarget.request();
    }
}
