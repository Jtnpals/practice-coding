package patterns.decorator;

public class Americano implements Caffe {
    private int price;

    public Americano(int price) {
        this.price = price;
    }

    @Override
    public int countPrice() {
        return price;
    }

    @Override
    public String print() {
        String str = "Order Americano";
        return str;
    }
}
