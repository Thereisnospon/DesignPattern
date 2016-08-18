# 责任链模式


### 模式定义


避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止，这就是职责链模式。

在职责链模式中最关键的一点就是客户提交请求后，请求沿着链往下传递直到有一个处理者处理它，在这里客户无需关心它的请求是哪个处理者来处理，反正总有一个处理者会处理它的请求。

在这里客户端和处理者都没有对方明确的信息，同时处理者也不知道职责链中的结构。所以职责链可以简化对象的相互连接，他们只需要保存一个指向其后续者的引用，而不需要保存所有候选者的引用。

在职责链模式中我们可以随时随地的增加或者更改一个处理者，甚至可以更改处理者的顺序，增加了系统的灵活性。处理灵活性是增加了，但是有时候可能会导致一个请求无论如何也得不到处理，它会被放置在链末端，这个既是职责链的优点也是缺点。



### 模式结构

- Handler: 抽象处理者。定义了一个处理请求的方法。所有的处理者都必须实现该抽象类。 
- ConcreteHandler: 具体处理者。处理它所负责的请求，同时也可以访问它的后继者。如果它能够处理该请求则处理，否则将请求传递到它的后继者。 
- Client: 客户类。



### 优点

- 降低耦合度。它将请求的发送者和接受者解耦。
- 简化了对象。使得对象不需要知道链的结构。
- 增强给对象指派职责的灵活性。通过改变链内的成员或者调动它们的次序，允许动态地新增或者删除责任。
- 增加新的请求处理类很方便。


### 缺点


- 不能保证请求一定被接收。
- 系统性能将受到一定影响，而且在进行代码调试时不太方便；可能会造成循环调用。
- 可能不容易观察运行时的特征，有碍于除错。

### 适用场景


- 有多个对象可以处理同一个请求，具体哪个对象处理该请求由运行时刻自动确定。
- 在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。
- 可动态指定一组对象处理请求。

```java

abstract class Handler{

    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    abstract void handle(int money);
}


class GroupLeader extends Handler{


    @Override
    void handle(int money) {
        if(money<100){
            System.out.println("组长已审批:"+money);
        }else{
            nextHandler.handle(money);
        }
    }
}
class Manager extends Handler{
    @Override
    void handle(int money) {
        if(money<500){
            System.out.println("经理已审批:"+money);
        }else{
            nextHandler.handle(money);
        }
    }
}
class Boss extends Handler{
    @Override
    void handle(int money) {
        System.out.println("Boss 已审批"+money);
    }
}

public class ResponsibilityDemo {

    public static void main(String []args){

        GroupLeader groupLeader=new GroupLeader();
        Manager manager=new Manager();
        Boss boss=new Boss();

        groupLeader.setNextHandler(manager);
        manager.setNextHandler(boss);

        groupLeader.handle(20);
        groupLeader.handle(200);
        groupLeader.handle(2000);

    }
}


```