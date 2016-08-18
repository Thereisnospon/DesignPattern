package mediator;

/**
 * Created by yzr on 16/8/18.
 */



abstract class Mediator{
    abstract void change(Colleague colleague);
}

abstract class Colleague{
    protected Mediator mediator;
    Colleague(Mediator mediator){
        this.mediator=mediator;
    }
}

class CdDriver extends Colleague{


    String data;


    public CdDriver(Mediator mediator) {
        super(mediator);
    }

    public String getData() {
        return data;
    }

    void readData(){
        data="cd:(2333";
        mediator.change(this);
    }
}

class Cpu extends Colleague{

    String data;

    public Cpu(Mediator mediator) {
        super(mediator);
    }

    public String getData() {
        return data;
    }

    void parseData(String msg){
        data="cpu:("+msg;
        mediator.change(this);
    }

}
class SoundCard extends Colleague{


    public SoundCard(Mediator mediator) {
        super(mediator);

    }

    void playSound(String msg){
        System.out.println("sound:("+msg);
        mediator.change(this);
    }

}

class MainBoard extends Mediator{

    Cpu cpu;
    CdDriver cdDriver;
    SoundCard soundCard;


    @Override
    void change(Colleague colleague) {
            if(colleague==cdDriver)
                handleCd();
            else if(colleague==cpu)
                handleCpu();
            else if(colleague==soundCard)
                handleSound();
    }

    void  handleCd(){
        String data=cdDriver.getData();
        cpu.parseData(data);
    }

    void handleCpu(){
        String data=cpu.getData();
        soundCard.playSound(data);
    }

    void handleSound(){
        System.out.println("播放完毕");
    }


    public void setCdDriver(CdDriver cdDriver) {
        this.cdDriver = cdDriver;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public void setSoundCard(SoundCard soundCard) {
        this.soundCard = soundCard;
    }
}


public class MediatorDemo {
    public static void main(String []args){
        MainBoard mainBoard=new MainBoard();
        Cpu cpu=new Cpu(mainBoard);
        CdDriver cdDriver=new CdDriver(mainBoard);
        SoundCard soundCard=new SoundCard(mainBoard);

        mainBoard.setCdDriver(cdDriver);
        mainBoard.setCpu(cpu);
        mainBoard.setSoundCard(soundCard);

        cdDriver.readData();

    }
}
