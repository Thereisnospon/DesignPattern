package command;

/**
 * Created by yzr on 16/5/19.
 */

class  Tv{
    public void turnUp(){
        System.out.println("turn up");
    }
    public void turnDown(){
        System.out.println("turn down");
    }
}


interface Command{
    public  void execute();
}

class  TurnUpCommand implements  Command{
    Tv mTv;
    TurnUpCommand(Tv tv){
        this.mTv=tv;
    }

    @Override
    public void execute() {
        mTv.turnUp();
    }
}

class TurnDownCommand implements Command{
    Tv mTv;
    TurnDownCommand(Tv tv){
        this.mTv=tv;
    }

    @Override
    public void execute() {
        mTv.turnDown();
    }
}



class  KeyPad
{
    Command turnDownCommand;
    Command turnUpCommand;

    public void setTurnUpCommand(Command turnUpCommand) {
        this.turnUpCommand = turnUpCommand;
    }

    public void setTurnDownCommand(Command turnDownCommand) {
        this.turnDownCommand = turnDownCommand;
    }

    public void turnDown(){
        turnDownCommand.execute();
    }

    public void turnUp(){
        turnUpCommand.execute();
    }

}


class  People {

    People(){
        Tv tv=new Tv();
        Command turnDownComand=new TurnDownCommand(tv);
        Command turnUpCommand=new TurnUpCommand(tv);

        KeyPad keyPad=new KeyPad();
        keyPad.setTurnDownCommand(turnDownComand);
        keyPad.setTurnUpCommand(turnUpCommand);

        keyPad.turnUp();
        keyPad.turnDown();

    }

}

public class CommandDemo {

    public static void main(String []args){

        System.out.print(Integer.toHexString(120));
    }

}
