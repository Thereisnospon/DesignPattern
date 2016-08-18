# 模板方法模式


### 模式定义

   所谓模板方法模式就是在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以在不改变算法结构的情况下，重新定义算法中的某些步骤。


模板方法模式是基于继承的代码复用技术的。在模板方法模式中，我们可以将相同部分的代码放在父类中，而将不同的代码放入不同的子类中。也就是说我们需要声明一个抽象的父类，将部分逻辑以具体方法以及具体构造函数的形式实现，然后声明一些抽象方法让子类来实现剩余的逻辑，不同的子类可以以不同的方式来实现这些逻辑。

其实所谓模板就是一个方法，这个方法将算法的实现定义成了一组步骤，其中任何步骤都是可以抽象的，交由子类来负责实现。这样就可以保证算法的结构保持不变，同时由子类提供部分实现。

模板是一个方法，那么他与普通的方法存在什么不同呢？模板方法是定义在抽象类中，把基本操作方法组合在一起形成一个总算法或者一组步骤的方法。而普通的方法是实现各个步骤的方法，我们可以认为普通方法是模板方法的一个组成部分。
 
 
### 模式结构

- AbstractClass: 抽象类
- ConcreteClass:  具体子类

其中抽象类提供一组算法和部分逻辑的实现，具体子类实现剩余逻辑。



### 优点

- 模板方法模式在定义了一组算法，将具体的实现交由子类负责。
- 模板方法模式是一种代码复用的基本技术。
- 模板方法模式导致一种反向的控制结构，通过一个父类调用其子类的操作，通过对子类的扩展增加新的行为，符合“开闭原则”。



### 缺点

 每一个不同的实现都需要一个子类来实现，导致类的个数增加，是的系统更加庞大。
 
 
 ### 适用场景
 
- 一次性实现一个算法的不变的部分，并将可变的行为留给子类来实现。
- 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复。
- 控制子类的扩展。



### 实例


```java

abstract class Computer{


    abstract void powerOn();
    abstract void loadOS();
    abstract void login();

    final void start(){
        powerOn();
        loadOS();
        login();
    }
}

class Lenovo extends Computer{
    @Override
    void powerOn() {
        System.out.println("联想电脑开机中..");
    }

    @Override
    void loadOS() {
        System.out.println("加载win10中..");
    }

    @Override
    void login() {
        System.out.println("win10登陆中");
    }
}
class Mac extends Computer{
    @Override
    void powerOn() {
        System.out.println("mac开机中..");
    }

    @Override
    void loadOS() {
        System.out.println("加载 os x中..");
    }

    @Override
    void login() {
        System.out.println("os x 登陆中..");
    }
}
public class TemplateMethodDemo {
    public static void main(String []args){
        Computer lenovo=new Lenovo();
        Computer mac=new Mac();

        lenovo.start();
        mac.start();
    }
}




```