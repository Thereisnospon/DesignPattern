package flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yzr on 16/8/17.
 */


interface Flyweight{
    void operation(String outerState);
}

class ConcreateFlyweight implements Flyweight{


    Character innerState;


    //内部状态,作为构造函数参数传入
    public ConcreateFlyweight(Character state) {
       this.innerState=state;
    }


    //外部状态,作为方法参数传入,改变方法行为,不改变内部状态
    @Override
    public void operation(String outerState) {
        System.out.printf("inner:%s,outer:%s\n",innerState,outerState);
    }
}

class FlyweightFactory{
    Map<Character,Flyweight>map=new HashMap<>();
    Flyweight get(Character state){
        Flyweight fly=map.get(state);
        if(fly==null){
            fly=new ConcreateFlyweight(state);
            map.put(state,fly);
        }
        return fly;
    }
}

class ConcreateCompositeFlyweight implements Flyweight {

    Map<Character,Flyweight>map=new HashMap<>();

    void add(Character state,Flyweight flyweight){
        map.put(state,flyweight);
    }

    @Override
    public void operation(String outerState) {
        for(Character key:map.keySet()){
            Flyweight fly=map.get(key);
            fly.operation(outerState);
        }
    }
}
class SuperFlyweightFactory{

    Map<Character,Flyweight>map=new HashMap<>();
    Flyweight get(List<Character>states){
        ConcreateCompositeFlyweight flyweight=new ConcreateCompositeFlyweight();
        for(Character state:states){
            flyweight.add(state,this.get(state));
        }
        return flyweight;
    }
    Flyweight get(Character state){
        Flyweight fly=map.get(state);
        if(fly==null){
            fly=new ConcreateFlyweight(state);
            map.put(state,fly);
        }
        return fly;
    }
}
public class FlyweightDemo {

    public static void main(String []args){
        FlyweightFactory flyweightFactory=new FlyweightFactory();
        Flyweight a=flyweightFactory.get('a');
        Flyweight b=flyweightFactory.get('a');
        System.out.println(a==b);
        a.operation("233");


        List<Character> list=new ArrayList<>();
        list.add('a');
        list.add('b');
        SuperFlyweightFactory factory=new SuperFlyweightFactory();
        Flyweight s1=factory.get(list);
        Flyweight s2=factory.get(list);

        System.out.println(s1==s2);
        s1.operation("out");

    }

}
