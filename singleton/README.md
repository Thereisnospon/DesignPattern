


## 模式定义

**单例模式(Singleton Pattern)**：单例模式确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例，这个类称为单例类，它提供全局访问的方法。

单例模式的要点有三个：一是某个类只能有一个实例；二是它必须自行创建这个实例；三是它必须自行向整个系统提供这个实例。单例模式是一种对象创建型模式。单例模式又名单件模式或单态模式。



##优点

- 提供了对唯一实例的受控访问。因为单例类封装了它的唯一实例，所以它可以严格控制客户怎样以及何时访问它，并为设计及开发团队提供了共享的概念。
- 由于在系统内存中只存在一个对象，因此可以节约系统资源，对于一些需要频繁创建和销毁的对象，单例模式无疑可以提高系统的性能。
- 允许可变数目的实例。我们可以基于单例模式进行扩展，使用与单例控制相似的方法来获得指定个数的对象实例。

##缺点

- 由于单例模式中没有抽象层，因此单例类的扩展有很大的困难。
- 单例类的职责过重，在一定程度上违背了“单一职责原则”。因为单例类既充当了工厂角色，提供了工厂方法，同时又充当了产品角色，包含一些业务方法，将产品的创建和产品的本身的功能融合到一起。
- 滥用单例将带来一些负面问题，如为了节省资源将数据库连接池对象设计为单例类，可能会导致共享连接池对象的程序过多而出现连接池溢出；现在很多面向对象语言(如Java、C#)的运行环境都提供了自动垃圾回收的技术，因此，如果实例化的对象长时间不被利用，系统会认为它是垃圾，会自动销毁并回收资源，下次利用时又将重新实例化，这将导致对象状态的丢失。


##适用环境
在以下情况下可以使用单例模式：

- 系统只需要一个实例对象，如系统要求提供一个唯一的序列号生成器，或者需要考虑资源消耗太大而只允许创建一个对象。
- 客户调用类的单个实例只允许使用一个公共访问点，除了该公共访问点，不能通过其他途径访问该实例。
- 在一个系统中要求一个类只有一个实例时才应当使用单例模式。反过来，如果一个类可以有几个实例共存，就需要对单例模式进行改进，使之成为多例模式





## 实现


### 懒汉，线程不安全


```

//懒汉,线程不安全
class ManagerA{

    private static ManagerA instance;
    private ManagerA(){

    }
    public static ManagerA getInstance(){
        if(instance==null){
            instance=new ManagerA();
        }
        return instance;
    }
}


```

简洁明了，但是不能在多线程下工作。


### 懒汉，线程安全

```java

//懒汉,线程安全
class ManagerB{

    private static ManagerB instance;
    private ManagerB(){

    }
    public static synchronized ManagerB getInstance(){
        if(instance==null){
            instance=new ManagerB();
        }
        return instance;
    }
}

```

线程安全，但是因为加锁，效率太低，大多数情况不需要同步。



### 双重检验锁


```java

//双重检验锁
class ManagerC{
    private static volatile ManagerC instance;
    private ManagerC(){}
    public static ManagerC getInstance(){
        if(instance==null){
            synchronized (ManagerC.class){
                if(instance==null){
                    instance=new ManagerC();
                }
            }
        }
        return instance;
    }
}


```

看起来没什么问题，但是Java指令中创建对象和赋值操作是分开进行的，也就是说instance = new Singleton();语句是分两步执行的。但是JVM并不保证这两个操作的先后顺序，也就是说有可能JVM会为新的Singleton实例分配空间，然后直接赋值给instance成员，然后再去初始化这个Singleton实例。这样就可能出错了


### 创建同步


```java

class ManagerG{
    private ManagerG(){}
    public static ManagerG instance;
    public  synchronized static void syncInit(){
        instance=new ManagerG();
    }
    public static ManagerG getInstance(){
        if(instance==null){
            syncInit();
        }
        return instance;
    }
}

```
也有人这样实现：因为我们只需要在创建类的时候进行同步，所以只要将创建和getInstance()分开，单独为创建加synchronized关键字.考虑性能的话，整个程序只需创建一次实例，所以性能也不会有什么影响


### 饿汉


```java

//饿汉式
class ManagerD{
    private static final ManagerD instance=new ManagerD();
    private ManagerD(){

    }
    public static ManagerD getInstance(){
        return instance;
    }
}


```

instance 在类加载时就实例化，类加载的原因可能有很多，但是可能由于加载过慢，需要用到的时候才想进行加载。

### 静态内部类


```java

//静态内部类
class ManagerE{

    private ManagerE(){

    }
    private static  class ManagerEHolder{
        private static ManagerE INSTANCE=new ManagerE();
    }
    public static ManagerE getInstance(){
        return ManagerEHolder.INSTANCE;
    }

}
```


把实例放在静态内部类里，只有第一次用到getInstance()方法的时候，会触发静态内部类ManagerEHolder 的类加载，JVM内部的机制能够保证当一个类被加载的时候，这个类的加载过程是线程互斥的。这样当我们第一次调用getInstance的时候，JVM能够帮我们保证instance只被创建一次，并且会保证把赋值给instance的内存初始化完毕，


### 枚举

```java


enum ManagerF{
    INSTANCE;
    public static ManagerF getInstance(){
        return INSTANCE;
    }
    private ManagerF(){

    }
}

````

这种方式是Effective Java作者Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象

