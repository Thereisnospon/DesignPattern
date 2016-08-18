package memoto;

import java.util.Stack;

/**
 * Created by yzr on 16/8/18.
 */



class Memoto{
    String name;
    int level;

    public Memoto(String name, int level) {
        this.name = name;
        this.level = level;
    }
}

class Player{

    String name;
    int level;


    Player(String name){
        this.name=name;
        this.level=1;
    }

    void levelUp(){
        System.out.println("玩家"+name+"升级了"+(++level));
    }

    void dead(){
        level=1;
        System.out.println("玩家掉回了"+level);
    }


    Memoto createMemoto(){
        return new Memoto(name,level);
    }

    void loadFromMemoto(Memoto memoto){
        this.name=memoto.name;
        this.level=memoto.level;
    }


}

class PlayerCaretaker{

    Stack<Memoto>stack=new Stack<>();

    void save(Memoto memoto){
        stack.push(memoto);
    }

    Memoto load(){
        return stack.empty()?null:stack.peek();
    }

    void remove(){
        if(!stack.empty()){
            stack.pop();
        }
    }

}

public class MemotoDemo {

    public static void main(String []args){
        Player player=new Player("小明");
        player.levelUp();
        player.levelUp();
        PlayerCaretaker caretaker=new PlayerCaretaker();
        caretaker.save(player.createMemoto());
        player.dead();
        player.loadFromMemoto(caretaker.load());
        System.out.println(player.level);
    }
}
