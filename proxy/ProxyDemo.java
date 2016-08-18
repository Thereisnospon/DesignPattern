package proxy;

import java.util.Random;

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

    boolean check() {
        Random random = new Random();
        return random.nextInt()>100;
    }

    @Override
    public void run() {
        if(check()){
            System.out.println("验证成功..");
            computer.run();
        }else{
            System.out.println("验证失败");
        }

    }


}

public class ProxyDemo {

    public static void main(String []args){
        ComputerProxy computerProxy=new ComputerProxy();
        computerProxy.run();
    }
}
