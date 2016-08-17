package decorator;

/**
 * Created by yzr on 16/8/17.
 */





interface Component {
    void operation();
}

class ConcreateComponent implements Component{
    @Override
    public void operation() {
        System.out.println("登陆中...");
    }
}

abstract class Decorator implements Component{

    Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

class ConcreateDecorator extends Decorator{

    public ConcreateDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("快速安全检查");
        super.operation();
    }
}

public class DecoratorDemo {

    public static void main(String []args){
        Component component=new ConcreateDecorator(new ConcreateComponent());
        component.operation();
    }
}
