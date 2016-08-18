package responsibility_chain;

/**
 * Created by yzr on 16/8/18.
 */


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
