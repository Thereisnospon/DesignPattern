package simple_factory;

/**
 * Created by yzr on 16/5/22.
 */


/**
 * 抽象产品 AbstractProduct
 */
interface Shape{
    public void draw();
}

/**
 * 具体产品 ConcreateProduct
 */
class  Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

/**
 * 具体产品  ConcreateProduct
 */
class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}

/**
 * 简单工厂 SimpleFactory
 */
class ShapeFactory
{

    public static Shape create(String name){
        Shape shape=null;
        if ("Circle".equals(name)){
            shape=new Circle();
        }else if("Rectangle".equals(name)){
            shape=new Rectangle();
        }
        return shape;
    }
}

public class SimpleFactoryDemo {

    public static  void main(String[]args){
            Shape circle=ShapeFactory.create("Circle");
            Shape rectangle=ShapeFactory.create("Rectangle");
            circle.draw();
            rectangle.draw();
    }

}
