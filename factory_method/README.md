




## 定义

工厂方法模式 （ Factory Method Pattern ） 又称为工厂模式，也叫虚拟构造器( Virtual Constructor )模式，或者多态工厂（Polymorphic Factory ) 模式，
工厂方法模式中，工厂父类负负责定义创建产品对象的公共接口，而工厂子类负责生产具体的产品对象。可以将产品的实例化操作延迟到工厂子类中完成。

## 结构
- *Product*
  抽象产品
- *ConcreateProduct*
  具体产品
- *Factory*
  抽象工厂
- *ConcreateFactory*
  具体工厂

## 分析
相对于简单工厂模式，核心的工厂类不再负责所有产品的创建，而是将具体创建工作交给子类去做。核心工厂类仅仅负责给出具体工厂必需要实现的接口，而不负责具体哪一个产品被实例化的细节，使得工厂方法模式可以在不修改工厂角色的情况下添加新的产品。

## 优点

- 向用户隐藏了哪种具体产品类被实例化的细节，用户只需要关心所需要产品对应的工厂，无需关心创建细节，甚至无须知道具体产品的类名。
- 基于工厂角色和产品角色的多态设计，能够使工厂自主确定创建何种产品对象，而如何创建对象的细节封装在具体工厂里。正是因为所有具体工厂来自于同一个抽象父类，所以工厂方法模式又称为多态工厂模式。
- 当添加新的产品时无须修改抽象工厂和抽象产品提供的接口，也无需修改其它具体工厂和具体产品。只需要添加一个具体工厂和具体产品就好了。这样系统的扩展性变得非常好，完全符合“开闭原则”


## 缺点
- 添加新的产品时，不仅要编写新的具体产品类，还要提供相应的具体工厂类，系统中类的数量会成对增进。增加系统的复杂度。
- 在代码中使用抽象层进行定义，增加了系统的抽象性和理解难度。

## 适用环境
- 客户端不需要知道具体产品类名，只需要知道所对应的工厂。
- 客户端无须知道是哪一个工厂子类创建产品子类。

##  扩展
- 在抽象工厂中定义多个工厂方法，使得具体产品角色实现不同的工厂方法，以满足对不同产品对象的需求。
- 产品对象重复使用，工厂对象将创建过的产品保存到集合中，根据客户对产品的需求，从已有产品返回或者创建新的产品。
- 如果仅仅只有一个具体产品对象，那么就不需要抽象工厂角色了，退回到了简单工厂模式。
```java
package factory_method;

/**
 * Created by yzr on 16/5/22.
 */

/**
 * 抽象产品 Product
 */
interface Button{
    public void show();
}

/**
 * 具体产品 ConcreateProduct
 */
class WinButton implements Button{
    @Override
    public void show() {
        System.out.println("windows button");
    }
}

/**
 * 具体产品 ConcreateProduct
 */
class MacButton implements Button{
    @Override
    public void show() {
        System.out.println("mac button");
    }
}

/**
 * 抽象工厂 Factory
 */
interface ButtonFactory{
    Button createButton();
}

/**
 * 具体工厂 ConcreateFactory
 */
class WinButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        return new WinButton();
    }
}

/**
 * 具体工厂 ConcreateFactory
 */
class  MacButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }
}

public class FactoryMethodDemo {


    public static void main(String []args){

        ButtonFactory winButtonFactory=new WinButtonFactory();
        ButtonFactory macButtonFactory=new MacButtonFactory();

        Button winButton=winButtonFactory.createButton();
        Button macButton=macButtonFactory.createButton();

        winButton.show();
        macButton.show();
    }


}

```