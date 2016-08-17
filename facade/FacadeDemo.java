package facade;

/**
 * Created by yzr on 16/8/17.
 */


/**
 * subsystem 1
 */
class PowerManager {
    void turnOn(){
        System.out.println("开机中..");
    }
    void turnOff(){
        System.out.println("关机中..");
    }
}

/**
 * subsystem 2
 */
class ChannelManager{
    void prev(){
        System.out.println("上一个频道");
    }
    void next(){
        System.out.println("下一个频道");
    }
}

/**
 * Facade
 */
class TvController{
    PowerManager powerManager;
    ChannelManager channelManager;

    public TvController() {
        this.powerManager = new PowerManager();
        this.channelManager =new ChannelManager();
    }

    void turnOn(){
        powerManager.turnOn();
    }

    void turnOff(){
        powerManager.turnOff();
    }

    void prev(){
        channelManager.prev();
    }

    void next(){
        channelManager.next();
    }
}

/**
 * Client
 */
public class FacadeDemo {

    public static void main(String []args){
        TvController controller=new TvController();
        controller.turnOn();
        controller.next();
        controller.turnOff();
    }
}
