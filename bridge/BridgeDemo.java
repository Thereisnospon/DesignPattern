package bridge;

/**
 * Created by yzr on 16/8/17.
 */

interface Color{
    String getValue();
}
class Red implements Color{
    @Override
    public String getValue() {
        return "#FF0000";
    }
}
abstract class Pen{

    protected Color color;
    abstract String getSize();


    Pen(Color color){
        this.color=color;
    }

    void write(String str){
        System.out.printf("用%s型号,%s颜色墨水的毛笔,写出%s\n",
                getSize(),color.getValue(),str);
    }

}
class PenS1 extends Pen{

    public PenS1(Color color) {
        super(color);
    }

    @Override
    String getSize() {
        return "1";
    }
}
public class BridgeDemo {
    public static void main(String []args){
        Pen pen=new PenS1(new Red());
        pen.write("233");
    }
}
