
## 定义
简单工厂模式 （ Simple Factory Pattern ) ,又称为 ( Static Factory Method ) ,属于创建型设计模式。
可以根据参数的不同返回不同类的实例，通常由一个类负责创建其它类的实例。被创建的类通常有相同的父类。


## 结构
- *Factory*
  工厂角色： 负责实现创建所有实例的内部逻辑
- *Product*
  抽象产品角色: 所有创建的对象的父类，描述所有实例共有的共同接口
- *ConcreateProduct*
  具体产品角色：创建目标

## 优点
- 将对象的创建和本身业务处理分离，降低系统的耦合度。
- 只需要一个简单的参数就可以获得所需要的对象，而无需知道具体的创建细节。
- 通过引入配置文件，可以在不修改任何客户端代码的情况下更换和增加新的具体产品类，在一定程度上提高了系统的灵活性。


## 缺点
- 工厂类集中了所有产品的创建逻辑，如果不能正常工作，整个系统都要受到影响。
- 设计模式通病：增加类的数量
- 系统扩展难，一旦添加新产品，就得要修改工厂逻辑，违背了开闭原则。当产品数量过多，有可能造成工厂逻辑过于复杂，不利于系统的扩展和维护。
- 简单工厂模式使用了静态工厂方法，造成工厂角色无法形成基于继承的等级结构。

## 适用环境

- 工厂负责创建的对象比较少。
- 客户端只知道创建工厂类的参数，对于如何创建对象不关心；
- 客户端既不关心创建细节，甚至类名都不需要知道，只需知道具体所对应的参数。


```java
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


```
