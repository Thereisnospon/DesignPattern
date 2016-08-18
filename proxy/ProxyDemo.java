package proxy;

/**
 * Created by yzr on 16/8/18.
 */



interface Runnable{
    void run();
}

class Computer implements Runnable{
    @Override
    public void run() {
        System.out.println("运行中..");
    }
}
class ComputerProxy implements Runnable{

    Computer computer;

    public ComputerProxy() {
        this.computer = new Computer();
    }

    void before(){
        System.out.println("运行检查..");
    }

    @Override
    public void run() {
        before();
        computer.run();
        after();
    }

    void after(){
        System.out.println("保存数据..");

    }
}

public class ProxyDemo {

    public static void main(String []args){
        ComputerProxy computerProxy=new ComputerProxy();
        computerProxy.run();
    }
}
