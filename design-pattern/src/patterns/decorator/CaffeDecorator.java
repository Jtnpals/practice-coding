package patterns.decorator;

public abstract class CaffeDecorator implements Caffe {
    protected Caffe caffe;

    public CaffeDecorator(Caffe caffe) {
        this.caffe = caffe;
    }

}
