package observer;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yzr on 16/8/18.
 */


interface Observer{
    void update(String msg);
}

interface  Subject{
    void register(Observer observer);
    void remove(Observer observer);
    void notifyObservers();
}

class ConcreateSubject implements Subject{


    List<Observer>observers=new ArrayList<>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        Random random=new Random();
        String msg=random.nextInt()+"";
        for(Observer o:observers){
            o.update(msg);
        }

    }
}

class ConcreateObserver implements Observer{

    String name;

    ConcreateObserver(String name){
        this.name=name;
    }

    @Override
    public void update(String msg) {
        System.out.println(name+" recv:"+msg);
    }
}


public class ObserverDemo {

    public static void main(String []args){

        ConcreateSubject subject=new ConcreateSubject();

        ConcreateObserver oa=new ConcreateObserver("oa");
        subject.register(oa);

        subject.notifyObservers();

        ConcreateObserver ob=new ConcreateObserver("ob");
        subject.register(ob);

        subject.notifyObservers();

    }
}
