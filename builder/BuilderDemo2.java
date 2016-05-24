package builder;

/**
 * Created by yzr on 16/5/23.
 */


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
