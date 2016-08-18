package command;

/**
 * Created by yzr on 16/5/19.
 */


import java.util.ArrayList;
import java.util.List;

/**
 * Receiver
 */
class  Tv{
    public void turnUp(){
        System.out.println("turn up");
    }
    public void turnDown(){
        System.out.println("turn down");
    }
}

/**
 * Command
 */
interface Command{
    void execute();
}

/**
 * Concreate Command 1
 */
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

/**
 * Concreate Command 2
 */
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


/**
 * Invoker
 */
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


/**
 * 宏命令
 */
interface MacroCommand extends Command{
    void add(Command command);
    void remove(Command command);
}


class TvMacroCommand implements MacroCommand{

    List<Command>commands=new ArrayList<>();

    @Override
    public void add(Command command) {
        commands.add(command);
    }

    @Override
    public void remove(Command command) {
        commands.remove(command);
    }

    @Override
    public void execute() {
        for(Command cmd:commands){
            cmd.execute();
        }
    }
}

class Invoker{
    Command command;
    Invoker(Command command){
        this.command=command;
    }
    public void invoke(){
        command.execute();
    }
}



/**
 * Client
 */

public class CommandDemo {

    public static void main(String []args){
        Tv tv=new Tv();
        Command turnDownComand=new TurnDownCommand(tv);
        Command turnUpCommand=new TurnUpCommand(tv);

        KeyPad keyPad=new KeyPad();
        keyPad.setTurnDownCommand(turnDownComand);
        keyPad.setTurnUpCommand(turnUpCommand);

        keyPad.turnUp();
        keyPad.turnDown();




        TvMacroCommand macroCommand=new TvMacroCommand();
        macroCommand.add(turnUpCommand);
        macroCommand.add(turnDownComand);
        Invoker invoker=new Invoker(macroCommand);

        invoker.invoke();
    }

}
