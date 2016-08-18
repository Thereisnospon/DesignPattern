package state;

/**
 * Created by yzr on 16/8/18.
 */


interface TvState{
    void prevChannel();
    void nextChannel();
}

class PowerOnState implements TvState{
    @Override
    public void prevChannel() {
        System.out.println("上一个频道");
    }

    @Override
    public void nextChannel() {
        System.out.println("下一个频道");
    }
}
class PowerOffState implements TvState{
    @Override
    public void prevChannel() {

    }

    @Override
    public void nextChannel() {

    }
}

interface Controller{
    void  prev();
    void  next();
    void changeState(TvState state);
}

class TvController implements Controller{

    TvState state;

    TvController(){
        state=new PowerOffState();
    }

    @Override
    public void prev() {
        state.prevChannel();
    }

    @Override
    public void next() {
        state.nextChannel();
    }

    @Override
    public void changeState(TvState state) {
        this.state=state;
    }
}

public class StateDemo {

    public static void main(String []args){
        TvController controller=new TvController();
        controller.prev();
        TvState onState=new PowerOnState();
        controller.changeState(onState);
        controller.prev();
    }
}
