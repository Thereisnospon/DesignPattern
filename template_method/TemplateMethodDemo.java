package template_method;

/**
 * Created by yzr on 16/8/18.
 */


abstract class Computer{


    abstract void powerOn();
    abstract void loadOS();
    abstract void login();

    final void start(){
        powerOn();
        loadOS();
        login();
    }
}

class Lenovo extends Computer{
    @Override
    void powerOn() {
        System.out.println("联想电脑开机中..");
    }

    @Override
    void loadOS() {
        System.out.println("加载win10中..");
    }

    @Override
    void login() {
        System.out.println("win10登陆中");
    }
}
class Mac extends Computer{
    @Override
    void powerOn() {
        System.out.println("mac开机中..");
    }

    @Override
    void loadOS() {
        System.out.println("加载 os x中..");
    }

    @Override
    void login() {
        System.out.println("os x 登陆中..");
    }
}
public class TemplateMethodDemo {
    public static void main(String []args){
        Computer lenovo=new Lenovo();
        Computer mac=new Mac();

        lenovo.start();
        mac.start();
    }
}
