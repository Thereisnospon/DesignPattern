


## 动机

软件开发中有一些复杂的对象，拥有一系列的成员属性，其中有些海华丝引用类型的成员对象。在这个复杂的对象中，有一些限制条件，比如某些属性没有赋值则复杂对象不能作为一个完整的产品使用，或者某些属性的赋值必须按照一定顺序，一个属性没有赋值之前，另一个属性可能无法赋值等。
由于一个复杂对象的组建过程非常复杂，因此这些部件的组合过程被外部化到一个 建造者（Builder)中，建造者返回客户端一个完整的对象，而用户无需关心它的具体属性以及它们的组装方式。

## 定义
建造者模式(Builder Pattern) :将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以有不同的表示。 建造者模式是一步一步构建复杂对象，它允许用户通过指定复杂对象的类型和内容就可以构建它们，而不需要知道具体的细节。 也被称做 生成器模式

## 结构
- Builder :抽象建造者
- ConcreateBuilder:具体建造者
- Director:指挥者
- Product: 产品角色

## 分析
抽象建造者定义了创建产品的方法和返回方法；
建造者模式引入了一个指挥者类 Director ，该类的主要作用有两个：一方面隔离了客户与生产过程，另一方面它复杂控制产品的生成过程。指挥者对抽象建造者编程。
客户端中无需关心产品对象的创建过程，只需要确定建造者的类型就可以。

## 优点
- 用户无需知道产品内部组成的具体细节，讲产品本身与创建过程解耦，使得相同的创建过程可以创建不同的产品对象。
- 每一个具体建造者都相对独立，而与其他具体建造者无关，因此可以很方便的替换具体建造者或者增加新的建造者。用户使用不同的建造者可以创建不同的产品对象。
- 可以更加精细的控制产品的创建过程。
- 增加新的具体建造者无需修改该原有类库的代码，指挥者针对抽象建造者编程。系统扩展方便，符合开闭原则。

## 缺点

- 建造者模式创建的产品一般具有较多的共同点，组成部分相似，如果产品之间差异很大，不适合使用。
- 如果产品内部变化复杂，可能会导致需要定义很多具体建造者来实现变化，导致系统变得很庞大。


## 适用环境

- 需要生成的产品对象具有复杂的内部结构
- 需要生成的产品对象的属性相互依赖，需要指定其生成顺序
- 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。


## 扩展
- 省略抽象建造者。如果系统中只需要一个具体建造者，可以省略抽象建造者。
- 省略指挥者。在具体建造者只有一个的情况下，如果抽象建造者角色已经被省略掉，那么还可以省略指挥者。

## 与抽象工厂模式对比
- 建造者模式返回一个组装好的完整产品。而抽象工厂模式返回一系列相关的产品，而这些产品位于不同的产品等级结构，构成一个产品族。
- 抽象工厂模式通过实例化工厂类通过工厂方法获得所需要的产品对象，而建造者模式，客户端可以不直接调用建造者方法，而通过指挥者指导如何生成对象，侧重于一步一步建造一个完整的复杂对象。
- 抽象工厂模式可以对应于汽车配件生产工厂，而建造者模式可以对应于一个汽车组装工厂。


## 一般建造者模式

```java

package builder;

/**
 * 具体产品
 */
class Computer{

    private String cpu;
    private String mainBoard;
    private String screen;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard(String mainBoard) {
        this.mainBoard = mainBoard;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public void run(){
        System.out.println(cpu+mainBoard+screen);
    }
}

/**
 * 抽象建造者
 */
abstract class ComputerBuilder{

    protected Computer computer;
    ComputerBuilder(){
        computer=new Computer();
    }
    public abstract void buildCpu();
    public abstract void buildMainBorad();
    public abstract void buildScreen();
    Computer create(){
        return computer;
    }
}

/**
 * 具体建造者
 */

class SurfaceBookBuilder extends ComputerBuilder{

    @Override
    public void buildCpu() {
        computer.setCpu("intel i5");
    }

    @Override
    public void buildMainBorad() {
        computer.setMainBoard("xx-11");
    }

    @Override
    public void buildScreen() {
        computer.setScreen("2k screen");
    }
}

/**
 * 具体建造者
 */
class MacBookBuilder extends  ComputerBuilder{
    @Override
    public void buildCpu() {
        computer.setCpu("intel i7");
    }

    @Override
    public void buildMainBorad() {
        computer.setMainBoard("xx-77");
    }

    @Override
    public void buildScreen() {
        computer.setScreen("4k screen");
    }
}

/**
 * 指挥者
 */
class Director{

    private ComputerBuilder builder;


    public void setBuilder(ComputerBuilder builder) {
        this.builder = builder;
    }

    public void construct(){
        builder.buildMainBorad();
        builder.buildCpu();
        builder.buildScreen();
    }

}

public class BuilderDemo2 {

    public static void main(String []args){

        Director director=new Director();


        MacBookBuilder macBookBuilder=new MacBookBuilder();
        director.setBuilder(macBookBuilder);
        director.construct();
        Computer macBook=macBookBuilder.create();
        macBook.run();


        SurfaceBookBuilder surfaceBookBuilder=new SurfaceBookBuilder();
        director.setBuilder(surfaceBookBuilder);
        director.construct();
        Computer surfaceBook=surfaceBookBuilder.create();
        surfaceBook.run();
    }
}

```

## 扩展

```java
package builder;

/**
 * 省略了抽象建造者和导演
 * 使构造函数私有,只能通过内部的建造者创建
 */
class  Dialog{

    private String title;
    private String msg;
    private String img;

    private Dialog(DialogBuilder builder){
        this.title=builder.title;
        this.msg=builder.msg;
        this.img=builder.img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void show(){
        System.out.printf("Title:%s\tMsg:%s\tImg:%s\n",title,msg,img);
    }


    public static  class  DialogBuilder
    {
        private String title="no title";
        private String msg="no msg";
        private String img="no img";

        DialogBuilder(){

        }

        public DialogBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public DialogBuilder setImg(String img) {
            this.img = img;
            return this;
        }

        public DialogBuilder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Dialog create(){
            return new Dialog(this);
        }
    }
}

public class BuilderDemo {

    public static void main(String []args){
            new Dialog.DialogBuilder()
                    .setTitle("hello")
                    .setMsg("world")
                    .setImg("good")
                    .create()
                    .show();
    }
}

```
