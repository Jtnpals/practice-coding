package patterns.adapter;

public class AdapterTest {
    public static void main(String[] args) {
        OldTarget oldTarget = new OldAdaptee();
        oldTarget.request();
        NewTarget newTarget = new NewAdaptee();
        newTarget.request();
//        OldTarget oldTarget1 = (OldTarget) new NewAdaptee(); 에러남
        Adapter adapter = new Adapter(new NewAdaptee());
        Client client = new Client(adapter);
        client.print();
    }
}
