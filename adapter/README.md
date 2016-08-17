# 适配器模式

### 模式定义

适配器模式(Adapter Pattern) ：将一个接口转换成客户希望的另一个接口，适配器模式使接口不兼容的那些类可以一起工作，其别名

### 模式结构


- **Target**：目标抽象类
- **Adapter**：适配器类
- **Adaptee**：适配者类
- **Client**：客户类




**对象适配器**


![image](http://design-patterns.readthedocs.io/zh_CN/latest/_images/Adapter.jpg)


**类适配器**


![image](http://design-patterns.readthedocs.io/zh_CN/latest/_images/Adapter_classModel.jpg)


### 优点


- 将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，而无须修改原有代码。
- 增加了类的透明性和复用性，将具体的实现封装在适配者类中，对于客户端类来说是透明的，而且提高了适配者的复用性。
- 灵活性和扩展性都非常好，通过使用配置文件，可以很方便地更换适配器，也可以在不修改原有代码的基础上增加新的适配器类，完全符合“开闭原则”。


**类适配器模式还具有如下优点**：

由于适配器类是适配者类的子类，因此可以在适配器类中置换一些适配者的方法，使得适配器的灵活性更强。

**对象适配器模式还具有如下优点**：

一个对象适配器可以把多个不同的适配者适配到同一个目标，也就是说，同一个适配器可以把适配者类和它的子类都适配到目标接口。


### 缺点

**类适配器模式的缺点如下：**

对于Java、C#等不支持多重继承的语言，一次最多只能适配一个适配者类，而且目标抽象类只能为抽象类，不能为具体类，其使用有一定的局限性，不能将一个适配者类和它的子类都适配到目标接口。

**对象适配器模式的缺点如下：**

与类适配器模式相比，要想置换适配者类的方法就不容易。如果一定要置换掉适配者类的一个或多个方法，就只好先做一个适配者类的子类，将适配者类的方法置换掉，然后再把适配者类的子类当做真正的适配者进行适配，实现过程较为复杂。

### 适用环境


- 系统需要使用现有的类，而这些类的接口不符合系统的需要。
- 想要建立一个可以重复使用的类，用于与一些彼此之间没有太大关联的一些类，包括一些可能在将来引进的类一起工作。



### 用例


```java
/**
 * Target
 */
interface IPlus{
    int add(int a,int b);
}

/**
 * Adaptee
 */
class Calculate{
    int calculate(int a,int b,char op){
        switch (op){
            case '+':return a+b;
            case '-':return a-b;
            case '*':return a*b;
            case '/':return a/b;
        }
        throw new IllegalArgumentException();
    }
}

/**
 * Class Adapter
 */
class PlusAdapter extends Calculate implements IPlus{
    @Override
    public int add(int a, int b) {
        return calculate(a,b,'+');
    }
}

/**
 * Object Adapter
 */
class ObjectAdapter implements IPlus{


    Calculate mCalculate;

    ObjectAdapter(Calculate calculate){
        this.mCalculate=calculate;
    }

    @Override
    public int add(int a, int b) {
        return mCalculate.calculate(a,b,'+');
    }
}
public class AdapterTest {

    public static void main(String []args){
        IPlus plus=new PlusAdapter();
        IPlus oplus=new ObjectAdapter(new Calculate());
        System.out.println(plus.add(1,2));
        System.out.println(oplus.add(2,3));
    }
}


```




###类适配器和对象适配器的权衡

- 类适配器使用对象继承的方式，是静态的定义方式；而对象适配器使用对象组合的方式，是动态组合的方式。
- 对于类适配器，由于适配器直接继承了Adaptee，使得适配器不能和Adaptee的子类一起工作，因为继承是静态的关系，当适配器继承了Adaptee后，就不可能再去处理Adaptee的子类了。对于对象适配器，一个适配器可以把多种不同的源适配到同一个目标。换言之，同一个适配器可以把源类和它的子类都适配到目标接口。因为对象适配器采用的是对象组合的关系，只要对象类型正确，是不是子类都无所谓。
- 对于类适配器，适配器可以重定义Adaptee的部分行为，相当于子类覆盖父类的部分实现方法。对于对象适配器，要重定义Adaptee的行为比较困难，这种情况下，需要定义Adaptee的子类来实现重定义，然后让适配器组合子类。虽然重定义Adaptee的行为比较困难，但是想要增加一些新的行为则方便的很，而且新增加的行为可同时适用于所有的源。
- 对于类适配器，仅仅引入了一个对象，并不需要额外的引用来间接得到Adaptee。对于对象适配器，需要额外的引用来间接得到Adaptee。

　　建议尽量使用对象适配器的实现方式，多用合成/聚合、少用继承。当然，具体问题具体分析，根据需要来选用实现方式，最适合的才是最好的。 　　


