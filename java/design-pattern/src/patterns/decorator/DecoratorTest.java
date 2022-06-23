package patterns.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        Caffe caffe = new ChocoChip(new WhippingCream(new Mocha(5000)));
        System.out.println(caffe.print());
        System.out.println("total : " + caffe.countPrice());
    }

}
