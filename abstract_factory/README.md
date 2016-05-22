




## 动机
在工厂方法模式中，每一个具体工厂对应一种具体产品，一般情况下，一个具体工厂只有一个工厂方法或者一组重载的工厂方法。但是有时候我们需要一个工厂可以提供多个产品对象，而不是单一的产品对象。为了更好理解工厂方法模式，引入两个概念：
- *产品等级结构*
  产品等级结构即产品的继承结构，如果一个抽象类是cpu，那么amd的cpu，intel的cpu这些具体品牌的cpu都是继承自抽象的cpu类，。抽象cpu与具体cpu之间就构成了一个产品等级结构。
- *产品族*
  产品族是指同一个工厂生产的，位于不同产品等级结构的一组产品。例如海尔厂生产的海尔电视机，海尔电冰箱就属于一个海尔厂的产品族中，海尔电视机属于电视机等级结构，而海尔电冰箱属于电冰箱等级结构。

  当系统中提供的工厂所需要生产的产品不仅仅是一个简单的对象，而是多个位于不同产品等级结构的具体产品时，应当使用抽象工厂模式。
  抽象工厂模式与工厂模式的区别在于：工厂模式只针对同一个产品等级结构，抽象工厂模式需要面对多个产品等级结构。

## 定义
 抽象工厂模式（Abstract Factory Pattern) :提供创建一系列相关或相互依赖对象的接口，而无须指定它们具体的类。又称为Kit模式

## 结构
- *AbstractFactory* 
  抽象工厂
- *ConcreateFactory*
  具体工厂
- *AbstractProduct*
  抽象产品
- *Product*
  具体产品

## 优点
- 抽象工厂模式隔离了具体类的生产，使用户不知道什么被创建。由于这种隔离，更换一个具体工厂变的非常容易。
- 当一个产品族的多个对象被设计在一起工作时，它能保证客户端使用的是同一个产品族中的对象，对于一些需要根据当前环境来决定其行为的软件系统来说，是一种非常实用的设计模式。
- 增加新的具体工厂和产品族非常方便，无须修改原有系统，符合开闭原则。

## 缺点
- 增加新的产品对象时，难以扩展抽象工厂生产新的产品对象。
- 开闭原则的倾斜性，增加新的工厂和产品族非常容易，增加新的产品等级结构非常麻烦。


## 适用

- 一个系统不应当依赖于具体产品如何被创建，组合和表达的细节。
- 系统中有多个产品族，且每次只使用其中某一个产品族。
- 属于同一个产品族的产品将在一起使用。
- 系统提供一个产品类的库，所有产品以同样的接口出现。

## 应用
- 主题换肤

## 扩展
- 增加产品族简单，增加产品等级结构复杂
- 退化：当每一个具体工厂只创建一个产品对象时，也就是只有一个产品等级结构，抽象工厂模式退化为工厂方法模式。当抽象工厂与具体工厂合并，也就是只有一种产品族时，退化为简单工厂模式。


```java
package abstract_factory;

/**
 * Created by yzr on 16/5/9.
 */

/**
 * 抽象产品:CPU
 */
interface  ICpu{
    void calculate();
}

/**
 * 抽象产品:MainBord
 */
interface  IMainBord{
    void  work();
}

/**
 * 抽象工厂
 */
interface  IFactory{
    ICpu createCpu();
    IMainBord createMainBord();
}

/**
 * 具体产品: IntelCpu
 * 产品族: Intel
 * 产品等级结构: CPU
 */
class  IntelCpu implements  ICpu{
    @Override
    public void calculate() {
        System.out.println("intel cpu");
    }
}

/**
 * 具体产品: AmdCpu
 * 产品族: Amd
 * 产品等级结构: CPU
 */
class  AmdCpu implements  ICpu{

    @Override
    public void calculate() {
        System.out.println("amd cpu");
    }
}

/**
 * 具体产品: IntelMainBord
 * 产品族: Intel
 * 产品等级结构: MainBord
 */
class  IntelMainBord implements  IMainBord{
    @Override
    public void work() {
        System.out.println("intel mainbord");
    }
}

/**
 *具体产品: AmdMainBord
 * 产品族: Amd
 * 产品等级结构: MainBord
 */

class  AmdMainBord implements  IMainBord{
    @Override
    public void work() {
        System.out.println("amd mainbord");
    }
}

/**
 * 具体工厂: Intel
 * 负责生产 Intel 族的产品
 */
class  IntelFactory implements  IFactory{
    @Override
    public ICpu createCpu() {
        return new IntelCpu();
    }

    @Override
    public IMainBord createMainBord() {
        return new IntelMainBord();
    }
}
/**
 * 具体工厂: Amd
 * 负责生产 Amd 族的产品
 */
class  AmdFactory implements  IFactory{
    @Override
    public ICpu createCpu() {
        return  new AmdCpu();
    }

    @Override
    public IMainBord createMainBord() {
        return  new AmdMainBord();
    }
}
/**
 * 生产同一个产品族(同一个厂商)生产的产品进行工作
 */
class  ComputerEnginner {

    public  static void createComputer(int type){
        IFactory factory=null;
        switch (type)
        {
            case 1:
                factory=new IntelFactory();
                break;
            case 2:
                factory=new AmdFactory();
                break;
            default:
                break;
        }
        if(factory!=null){
            ICpu cpu=factory.createCpu();
            IMainBord mainBord=factory.createMainBord();
            cpu.calculate();
            mainBord.work();
        }
    }
}
public class AbstractFactoryDemo {
    public  static  void main(String []args){
        ComputerEnginner computerEnginner=new ComputerEnginner();
        computerEnginner.createComputer(1);
        computerEnginner.createComputer(2);
    }
}

```
