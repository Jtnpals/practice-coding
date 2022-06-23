package patterns.decorator;

public class WhippingCream extends CaffeDecorator {
    private int price = 500;

    public void setPrice(int price) {
        this.price = price;
    }

    public WhippingCream(Caffe caffe) {
        super(caffe);
    }

    @Override
    public int countPrice() {
        return caffe.countPrice() + price;
    }

    @Override
    public String print() {
        String str = " +WhippingCream";
        return caffe.print() + str;
    }
}
