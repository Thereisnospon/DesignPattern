package singleton;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * Created by yzr on 16/6/19.
 */

//懒汉,线程不安全
class ManagerA{

    private static ManagerA instance;
    private ManagerA(){

    }
    public static ManagerA getInstance(){
        if(instance==null){
            instance=new ManagerA();
        }
        return instance;
    }
}
//懒汉,线程安全
class ManagerB{

    private static ManagerB instance;
    private ManagerB(){

    }
    public static synchronized ManagerB getInstance(){
        if(instance==null){
            instance=new ManagerB();
        }
        return instance;
    }
}
//双重检验锁
class ManagerC{
    private static volatile ManagerC instance;
    private ManagerC(){}
    public static ManagerC getInstance(){
        if(instance==null){
            synchronized (ManagerC.class){
                instance=new ManagerC();
            }
        }
        return instance;
    }
}
//饿汉式
class ManagerD{
    private static final ManagerD instance=new ManagerD();
    private ManagerD(){

    }
    public static ManagerD getInstance(){
        return instance;
    }
}
//静态内部类
class ManagerE{

    private ManagerE(){

    }
    private static  class ManagerEHolder{
        private static ManagerE INSTANCE=new ManagerE();
    }
    public static ManagerE getInstance(){
        return ManagerEHolder.INSTANCE;
    }

}
enum ManagerF{
    INSTANCE;
    public static ManagerF getInstance(){
        return INSTANCE;
    }
    private ManagerF(){

    }
}

public class SingletonDemo {
    public static void main(String []args){
        ManagerA managerA=ManagerA.getInstance();
        ManagerB managerB=ManagerB.getInstance();
        ManagerC managerC=ManagerC.getInstance();
        ManagerD managerD=ManagerD.getInstance();
        ManagerE managerE=ManagerE.getInstance();
        ManagerF managerF=ManagerF.INSTANCE;
    }
}
