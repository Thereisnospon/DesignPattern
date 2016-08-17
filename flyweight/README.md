# 享元模式


### 模式动机


面向对象技术可以很好地解决一些灵活性或可扩展性问题，但在很多情况下需要在系统中增加类和对象的个数。当对象数量太多时，将导致运行代价过高，带来性能下降等问题。

- 享元模式正是为解决这一类问题而诞生的。享元模式通过共享技术实现相同或相似对象的重用。
- 在享元模式中可以共享的相同内容称为内部状态(IntrinsicState)，而那些需要外部环境来设置的不能共享的内容称为外部状态(Extrinsic State)，由于区分了内部状态和外部状态，因此可以通过设置不同的外部状态使得相同的对象可以具有一些不同的特征，而相同的内部状态是可以共享的。
- 在享元模式中通常会出现工厂模式，需要创建一个享元工厂来负责维护一个享元池(Flyweight Pool)用于存储具有相同内部状态的享元对象。
- 在享元模式中共享的是享元对象的内部状态，外部状态需要通过环境来设置。在实际使用中，能够共享的内部状态是有限的，因此享元对象一般都设计为较小的对象，它所包含的内部状态较少，这种对象也称为细粒度对象。享元模式的目的就是使用共享技术来实现大量细粒度对象的复用。


### 模式定义

享元模式(Flyweight Pattern)：运用共享技术有效地支持大量细粒度对象的复用。系统只使用少量的对象，而这些对象都很相似，状态变化很小，可以实现对象的多次复用。由于享元模式要求能够共享的对象必须是细粒度对象，因此它又称为轻量级模式，它是一种对象结构型模式。



### 模式结构

　享元模式采用一个共享来避免大量拥有相同内容对象的开销。这种开销最常见、最直观的就是内存的损耗。享元对象能做到共享的关键是区分内蕴状态(Internal State)和外蕴状态(External State)。

一个内蕴状态是存储在享元对象内部的，并且是不会随环境的改变而有所不同。因此，一个享元可以具有内蕴状态并可以共享。

一个外蕴状态是随环境的改变而改变的、不可以共享的。享元对象的外蕴状态必须由客户端保存，并在享元对象被创建之后，在需要使用的时候再传入到享元对象内部。外蕴状态不可以影响享元对象的内蕴状态，它们是相互独立的。

享元模式可以分成单纯享元模式和复合享元模式两种形式。


#### 单纯享元模式


**抽象享元(Flyweight)角色** ：给出一个抽象接口，以规定出所有具体享元角色需要实现的方法。

**具体享元(ConcreteFlyweight)角色**：实现抽象享元角色所规定出的接口。如果有内蕴状态的话，必须负责为内蕴状态提供存储空间。

**享元工厂(FlyweightFactory)角色** ：本角色负责创建和管理享元角色。本角色必须保证享元对象可以被系统适当地共享。当一个客户端对象调用一个享元对象的时候，享元工厂角色会检查系统中是否已经有一个符合要求的享元对象。如果已经有了，享元工厂角色就应当提供这个已有的享元对象；如果系统中没有一个适当的享元对象的话，享元工厂角色就应当创建一个合适的享元对象。、



#### 复合享元模式


**抽象享元(Flyweight)角色** ：给出一个抽象接口，以规定出所有具体享元角色需要实现的方法。

**具体享元(ConcreteFlyweight)角色**：实现抽象享元角色所规定出的接口。如果有内蕴状态的话，必须负责为内蕴状态提供存储空间。

**复合享元(ConcreteCompositeFlyweight)角色** ：复合享元角色所代表的对象是不可以共享的，但是一个复合享元对象可以分解成为多个本身是单纯享元对象的组合。复合享元角色又称作不可共享的享元对象。

**享元工厂(FlyweightFactory)角色** ：本角 色负责创建和管理享元角色。本角色必须保证享元对象可以被系统适当地共享。当一个客户端对象调用一个享元对象的时候，享元工厂角色会检查系统中是否已经有 一个符合要求的享元对象。如果已经有了，享元工厂角色就应当提供这个已有的享元对象；如果系统中没有一个适当的享元对象的话，享元工厂角色就应当创建一个 合适的享元对象。




### 模式分析


享元模式是一个考虑系统性能的设计模式，通过使用享元模式可以节约内存空间，提高系统的性能。

享元模式的核心在于享元工厂类，享元工厂类的作用在于提供一个用于存储享元对象的享元池，用户需要对象时，首先从享元池中获取，如果享元池中不存在，则创建一个新的享元对象返回给用户，并在享元池中保存该新增对象。

享元模式以共享的方式高效地支持大量的细粒度对象，享元对象能做到共享的关键是区分内部状态(Internal State)和外部状态(External State)。

- 内部状态是存储在享元对象内部并且不会随环境改变而改变的状态，因此内部状态可以共享。
- 外部状态是随环境改变而改变的、不可以共享的状态。享元对象的外部状态必须由客户端保存，并在享元对象被创建之后，在需要使用的时候再传入到享元对象内部。一个外部状态与另一个外部状态之间是相互独立的。


### 优点

- 享元模式的优点在于它可以极大减少内存中对象的数量，使得相同对象或相似对象在内存中只保存一份。
- 享元模式的外部状态相对独立，而且不会影响其内部状态，从而使得享元对象可以在不同的环境中被共享。


### 缺点

- 享元模式使得系统更加复杂，需要分离出内部状态和外部状态，这使得程序的逻辑复杂化。
- 为了使对象可以共享，享元模式需要将享元对象的状态外部化，而读取外部状态使得运行时间变长。


### 适用场景

- 一个系统有大量相同或者相似的对象，由于这类对象的大量使用，造成内存的大量耗费。
- 对象的大部分状态都可以外部化，可以将这些外部状态传入对象中。
- 使用享元模式需要维护一个存储享元对象的享元池，而这需要耗费资源，因此，应当在多次重复使用享元对象时才值得使用享元模式。


### 模式扩展

- 单纯享元模式：在单纯享元模式中，所有的享元对象都是可以共享的，即所有抽象享元类的子类都可共享，不存在非共享具体享元类。
- 复合享元模式：将一些单纯享元使用组合模式加以组合，可以形成复合享元对象，这样的复合享元对象本身不能共享，但是它们可以分解成单纯享元对象，而后者则可以共享。

### 与其它模式联动


- 在享元模式的享元工厂类中通常提供一个静态的工厂方法用于返回享元对象，使用简单工厂模式来生成享元对象。
- 在一个系统中，通常只有唯一一个享元工厂，因此享元工厂类可以使用单例模式进行设计。
- 享元模式可以结合组合模式形成复合享元模式，统一对享元对象设置外部状态。




### 应用

如 java 的 String 对象


### 实例


```java

interface Flyweight{
    void operation(String outerState);
}

class ConcreateFlyweight implements Flyweight{


    Character innerState;


    //内部状态,作为构造函数参数传入
    public ConcreateFlyweight(Character state) {
       this.innerState=state;
    }


    //外部状态,作为方法参数传入,改变方法行为,不改变内部状态
    @Override
    public void operation(String outerState) {
        System.out.printf("inner:%s,outer:%s\n",innerState,outerState);
    }
}

class FlyweightFactory{
    Map<Character,Flyweight>map=new HashMap<>();
    Flyweight get(Character state){
        Flyweight fly=map.get(state);
        if(fly==null){
            fly=new ConcreateFlyweight(state);
            map.put(state,fly);
        }
        return fly;
    }
}

class ConcreateCompositeFlyweight implements Flyweight {

    Map<Character,Flyweight>map=new HashMap<>();

    void add(Character state,Flyweight flyweight){
        map.put(state,flyweight);
    }

    @Override
    public void operation(String outerState) {
        for(Character key:map.keySet()){
            Flyweight fly=map.get(key);
            fly.operation(outerState);
        }
    }
}
class SuperFlyweightFactory{

    Map<Character,Flyweight>map=new HashMap<>();
    Flyweight get(List<Character>states){
        ConcreateCompositeFlyweight flyweight=new ConcreateCompositeFlyweight();
        for(Character state:states){
            flyweight.add(state,this.get(state));
        }
        return flyweight;
    }
    Flyweight get(Character state){
        Flyweight fly=map.get(state);
        if(fly==null){
            fly=new ConcreateFlyweight(state);
            map.put(state,fly);
        }
        return fly;
    }
}
public class FlyweightDemo {

    public static void main(String []args){
        FlyweightFactory flyweightFactory=new FlyweightFactory();
        Flyweight a=flyweightFactory.get('a');
        Flyweight b=flyweightFactory.get('a');
        System.out.println(a==b);
        a.operation("233");


        List<Character> list=new ArrayList<>();
        list.add('a');
        list.add('b');
        SuperFlyweightFactory factory=new SuperFlyweightFactory();
        Flyweight s1=factory.get(list);
        Flyweight s2=factory.get(list);

        System.out.println(s1==s2);
        s1.operation("out");

    }

}

```