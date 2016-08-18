# 访问者模式

### 模式动机


在我们实际的软件开发过程中，有时候我们对同一个对象可能会有不同的处理，对相同元素对象也可能存在不同的操作方式，如处方单，划价人员要根据它来划价，药房工作者要根据它来给药。而且可能会随时增加新的操作，如医院增加新的药物。但是这里有两个元素是保持不变的，或者说很少变：划价人员和药房工作中，变的只不过是他们的操作。所以我们想如果能够将他们的操作抽象化就好了。这里访问者模式就是一个值得考虑的解决方案了。

访问者模式的目的是封装一些施加于某种数据结构元素之上的操作，一旦这些操作需要修改的话，接受这个操作的数据结构可以保持不变。为不同类型的元素提供多种访问操作方式，且可以在不修改原有系统的情况下增加新的操作方式，这就是访问者模式的模式动机。



### 模式定义


访问者模式即表示一个作用于某对象结构中的各元素的操作，它使我们可以在不改变各元素的类的前提下定义作用于这些元素的新操作。

首先我们要明确一点就是访问者模式适用于数据结构相对稳定的系统。它是将数据的操作与数据结构进行分离了，如果某个系统的数据结构相对稳定，但是操作算法易于变化的话，就比较适用适用访问者模式，因为访问者模式使得算法操作的增加变得比较简单了


### 模式结构

- Vistor: 抽象访问者。为该对象结构中的ConcreteElement的每一个类声明的一个操作。 
- ConcreteVisitor: 具体访问者。实现Visitor申明的每一个操作，每一个操作实现算法的一部分。 
- Element: 抽象元素。定义一个Accept操作，它以一个访问者为参数。 
- ConcreteElement: 具体元素 。实现Accept操作。 
- ObjectStructure: 对象结构。能够枚举它的元素，可以提供一个高层的接口来允许访问者访问它的元素。

在访问者模式中对象结构存储了不同类型的对象，以便不同的访问者来访问。从上面的UML结构图中我们可以看出，访问者模式主要分为两个层次结构，一个是访问者层次结构，提供了抽象访问者和具体访问者，主要用于什么一些操作。一个是元素层次结构，提供了抽象元素和具体元素，主要用于声明Accept操作。

在访问者模式中相同的访问者可以以不同的方式访问不同的元素，所以在访问者模式中增加新的访问者无需修改现有代码，可扩展行强。

-同时在访问者模式用到了一种双分派的技术，所谓双分派技术就是在选择一个方法的时候，不仅仅要根据消息接收者（receiver）的运行时区别（Run time type），还要根据参数的运行时区别。在访问者模式中，客户端将具体状态当做参数传递给具体访问者，这里完成第一次分派，然后具体访问者作为参数的“具体状态”中的方法，同时也将自己this作为参数传递进去，这里就完成了第二次分派。双分派意味着得到的执行操作决定于请求的种类和接受者的类型。

### 优点

- 使得新增新的访问操作变得更加简单。
- 能够使得用户在不修改现有类的层次结构下，定义该类层次结构的操作。
- 将有关元素对象的访问行为集中到一个访问者对象中，而不是分散搞一个个的元素类中。



### 缺点


- 增加新的元素类很困难。在访问者模式中，每增加一个新的元素类都意味着要在抽象访问者角色中增加一个新的抽象操作，并在每一个具体访问者类中增加相应的具体操作，违背了“开闭原则”的要求。 
- 破坏封装。当采用访问者模式的时候，就会打破组合类的封装。
- 比较难理解。貌似是最难的设计模式了。


### 适用场景



- 对象结构中对象对应的类很少改变，但经常需要在此对象结构上定义新的操作。
- 需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作“污染”这些对象的类，也不希望在增加新操作时修改这些类。


### 总结
 正如《设计模式》的作者GoF对访问者模式的描述：大多数情况下，你并需要使用访问者模式，但是当你一旦需要使用它时，那你就是真的需要它了。当然这只是针对真正的大牛而言。在现实情况下（至少是我所处的环境当中），很多人往往沉迷于设计模式，他们使用一种设计模式时，从来不去认真考虑所使用的模式是否适合这种场景，而往往只是想展示一下自己对面向对象设计的驾驭能力。编程时有这种心理，往往会发生滥用设计模式的情况。所以，在学习设计模式时，一定要理解模式的适用性。必须做到使用一种模式是因为了解它的优点，不使用一种模式是因为了解它的弊端；而不是使用一种模式是因为不了解它的弊端，不使用一种模式是因为不了解它的优点。

### 实例


```java

interface Visitor{
    void visitBook(Element book);
    void visitGame(Element game);
}


abstract class Element{
    String name;

    public Element(String name) {
        this.name = name;
    }

    abstract void accept(Visitor visitor);
}
class Book extends Element{
    public Book(String name) {
        super(name);
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visitBook(this);
    }
}

class Game extends Element{

    public Game(String name) {
        super(name);
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visitGame(this);
    }
}
class Student implements Visitor{


    @Override
    public void visitBook(Element book) {
        System.out.println("学生对"+book.name+"的评价为:"+70);
    }

    @Override
    public void visitGame(Element game) {
        System.out.println("学生对"+game.name+"的评价为:"+80);
    }
}
class Parent implements  Visitor{
    @Override
    public void visitBook(Element book) {
        System.out.println("家长对"+book.name+"的评价为"+80);
    }

    @Override
    public void visitGame(Element game) {
        System.out.println("家长对"+game.name+"的评价为"+70);
    }
}
class Teacher implements Visitor{
    @Override
    public void visitBook(Element book) {
        System.out.println("老师对"+book.name+"的评价为"+90);
    }

    @Override
    public void visitGame(Element game) {
        System.out.println("老师对"+game.name+"评价为"+60);
    }
}



public class VisitorDemo {


    public static void main(String []args){
        Book book=new Book("《三国演义》");
        Game game=new Game("真三国无双");


        Student student=new Student();
        Parent parent=new Parent();
        Teacher teacher=new Teacher();

        book.accept(student);
        book.accept(parent);
        book.accept(teacher);

        game.accept(student);
        game.accept(parent);
        game.accept(teacher);
    }
}

```