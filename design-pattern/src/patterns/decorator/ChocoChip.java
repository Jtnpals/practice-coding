package patterns.decorator;

public class ChocoChip extends CaffeDecorator {
    private int price =300;

    public void setPrice(int price) {
        this.price = price;
    }

    public ChocoChip(Caffe caffe) {
        super(caffe);
    }

    @Override
    public int countPrice() {
        return caffe.countPrice() + price;
    }

    @Override
    public String print() {
        String str = " +ChocoChip";
        return caffe.print() + str;
    }
}
