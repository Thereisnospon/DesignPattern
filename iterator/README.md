# 迭代器模式


### 模式定义


   何谓迭代器模式？所谓迭代器模式就是提供一种方法顺序访问一个聚合对象中的各个元素，而不是暴露其内部的表示。在实际的开发过程中，我们可能需要针对不同的需求，可能需要以不同的方式来遍历整个整合对象，但是我们不希望在聚合对象的抽象接口层中充斥着各种不同的便利操作。这个时候我们就需要这样一种东西，它应该具备如下三个功能：

- 能够便利一个聚合对象。
- 我们不需要了解聚合对象的内部结构。
- 能够提供多种不同的遍历方式。

这三个功能就是迭代器模式需要解决的问题。作为一个功能强大的模式，迭代器模式把在元素之间游走的责任交给迭代器，而不是聚合对象。这样做就简化了聚合的接口和实现，也可以让聚合更专注在它所应该专注的事情上，这样做就更加符合单一责任原则。


### 模式结构

- Iterator: 抽象迭代器：所有迭代器都需要实现的接口，提供了游走聚合对象元素之间的方法。
- ConcreteIterator: 具体迭代器。利用这个具体的迭代器能够对具体的聚合对象进行遍历。每一个聚合对象都应该对应一个具体的迭代器。
- Aggregate: 抽象聚合类。
- ConcreteAggregate: 具体聚合类。实现creatorIterator()方法，返回该聚合对象的迭代器。

### 优点

- 它支持以不同的方式遍历一个聚合对象。
- 迭代器简化了聚合类。
- 在同一个聚合上可以有多个遍历。
- 在迭代器模式中，增加新的聚合类和迭代器类都很方便，无须修改原有代码。


### 缺点
  由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加，这在一定程度上增加了系统的复杂性。

### 适用场景


- 访问一个聚合对象的内容而无须暴露它的内部表示。
- 需要为聚合对象提供多种遍历方式。
- 为遍历不同的聚合结构提供一个统一的接口


### 实例

```java

interface Iterator<T>{
    boolean hasNext();
    T next();
}
class StringIterator implements Iterator<String> {

    String strs[];
    int pos=0;

    StringIterator(String []strs){
        this.strs=strs;
        this.pos=0;
    }
    
    @Override
    public boolean hasNext() {
        return strs!=null&&pos<strs.length;
    }

    @Override
    public String next() {
        String item=strs[pos++];
        return item;
    }
}


interface  Aggragate<T>{
    void add(T t);
    Iterator<T>first();
}

class StringAggregate implements Aggragate<String>{

    public static final int MAX=3;

    String []strings;

    int pos=0;

    StringAggregate(){
        strings=new String[MAX];
        pos=0;
        add("c");
        add("java");
        add("python");
    }


    @Override
    public void add(String s) {
        if(pos<strings.length){
            strings[pos++]=s;
        }else{
            System.out.println("fulll !");
        }
    }

    @Override
    public Iterator<String> first() {
        return new StringIterator(strings);
    }
}
public class IteratorDemo {

    public static void main(String []args){

        StringAggregate aggregate=new StringAggregate();
        Iterator<String> iterator=aggregate.first();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}


```