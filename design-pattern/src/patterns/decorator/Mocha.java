package patterns.decorator;

public class Mocha implements Caffe {
    private int price;

    public Mocha(int price) {
        this.price = price;
    }

    @Override
    public int countPrice() {
        return price;
    }

    @Override
    public String print() {
        String str = "Order Mocha";
        return str;
    }
}
