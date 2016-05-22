package factory_method;

/**
 * Created by yzr on 16/5/22.
 */

/**
 * 抽象产品 Product
 */
interface Button{
    public void show();
}

/**
 * 具体产品 ConcreateProduct
 */
class WinButton implements Button{
    @Override
    public void show() {
        System.out.println("windows button");
    }
}

/**
 * 具体产品 ConcreateProduct
 */
class MacButton implements Button{
    @Override
    public void show() {
        System.out.println("mac button");
    }
}

/**
 * 抽象工厂 Factory
 */
interface ButtonFactory{
    Button createButton();
}

/**
 * 具体工厂 ConcreateFactory
 */
class WinButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        return new WinButton();
    }
}

/**
 * 具体工厂 ConcreateFactory
 */
class  MacButtonFactory implements ButtonFactory{
    @Override
    public Button createButton() {
        return new MacButton();
    }
}

public class FactoryMethodDemo {


    public static void main(String []args){

        ButtonFactory winButtonFactory=new WinButtonFactory();
        ButtonFactory macButtonFactory=new MacButtonFactory();

        Button winButton=winButtonFactory.createButton();
        Button macButton=macButtonFactory.createButton();

        winButton.show();
        macButton.show();
    }


}
