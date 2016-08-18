# 备忘录模式


### 模式定义


所谓备忘录模式就是在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先保存的状态。

备忘录模式将要保存的细节给封装在备忘录中，就是那天要改变保存的细节也不会影响到客户端。


### 模式结构

- Originator: 原发器。负责创建一个备忘录，用以记录当前对象的内部状态，通过也可以使用它来利用备忘录恢复内部状态。同时原发器还可以根据需要决定Memento存储Originator的那些内部状态。
- Memento: 备忘录。用于存储Originator的内部状态，并且可以防止Originator以外的对象访问Memento。在备忘录Memento中有两个接口，其中Caretaker只能看到备忘录中的窄接口，它只能将备忘录传递给其他对象。Originator可以看到宽接口，允许它访问返回到先前状态的所有数据。
- Caretaker: 负责人。负责保存好备忘录，不能对备忘录的内容进行操作和访问，只能够将备忘录传递给其他对象。

在备忘录模式中，最重要的就是备忘录Memento了。我们都是备忘录中存储的就是原发器的部分或者所有的状态信息，而这些状态信息是不能够被其他对象所访问了，也就是说我们是不可能在备忘录之外的对象来存储这些状态信息，如果暴漏了内部状态信息就违反了封装的原则，故备忘录是除了原发器外其他对象都是不可以访问的。

所以为了实现备忘录模式的封装，我们需要对备忘录的访问做些控制：

- 对原发器：可以访问备忘录里的所有信息。
- 对负责人：不可以访问备忘录里面的数据，但是他可以保存备忘录并且可以将备忘录传递给其他对象。
- 其他对象：不可访问也不可以保存，它只负责接收从负责人那里传递过来的备忘录同时恢复原发器的状态。

所以就备忘录模式而言理想的情况就是只允许生成该备忘录的那个原发器访问备忘录的内部状态。


### 优点


- 给用户提供了一种可以恢复状态的机制。可以是用户能够比较方便地回到某个历史的状态。
- 实现了信息的封装。使得用户不需要关心状态的保存细节。


### 缺点

消耗资源。如果类的成员变量过多，势必会占用比较大的资源，而且每一次保存都会消耗一定的内存。



### 适用场景

- 需要保存一个对象在某一个时刻的状态或部分状态。
- 如果用一个接口来让其他对象得到这些状态，将会暴露对象的实现细节并破坏对象的封装性，一个对象不希望外界直接访问其内部状态，通过负责人可以间接访问其内部状态。



```java

class Memoto{
    String name;
    int level;

    public Memoto(String name, int level) {
        this.name = name;
        this.level = level;
    }
}

class Player{

    String name;
    int level;


    Player(String name){
        this.name=name;
        this.level=1;
    }

    void levelUp(){
        System.out.println("玩家"+name+"升级了"+(++level));
    }

    void dead(){
        level=1;
        System.out.println("玩家掉回了"+level);
    }


    Memoto createMemoto(){
        return new Memoto(name,level);
    }

    void loadFromMemoto(Memoto memoto){
        this.name=memoto.name;
        this.level=memoto.level;
    }


}

class PlayerCaretaker{

    Stack<Memoto>stack=new Stack<>();

    void save(Memoto memoto){
        stack.push(memoto);
    }

    Memoto load(){
        return stack.empty()?null:stack.peek();
    }

    void remove(){
        if(!stack.empty()){
            stack.pop();
        }
    }

}

public class MemotoDemo {

    public static void main(String []args){
        Player player=new Player("小明");
        player.levelUp();
        player.levelUp();
        PlayerCaretaker caretaker=new PlayerCaretaker();
        caretaker.save(player.createMemoto());
        player.dead();
        player.loadFromMemoto(caretaker.load());
        System.out.println(player.level);
    }
}


```