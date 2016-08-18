package strategy;

import java.sql.Connection;

/**
 * Created by yzr on 16/8/18.
 */



interface Calculate{
    int calculate(int km);
}
class BusCalculate implements Calculate{
    @Override
    public int calculate(int km) {
        return 2;
    }
}
class TaxiCalculate implements Calculate{
    @Override
    public int calculate(int km) {
        return 2+km*2;
    }
}
class Context{
    Calculate mCalculate;

    public Context(Calculate calculate) {
        this.mCalculate = calculate;
    }

    public void setCalculate(Calculate calculate) {
        this.mCalculate = calculate;
    }

    int calculate(int km){
        return mCalculate.calculate(km);
    }
}

public class StrategyDemo {


    public static void main(String []args){

        Calculate bus=new BusCalculate();
        Calculate taxi=new TaxiCalculate();

        Context context=new Context(bus);
        System.out.println(context.calculate(2));

        context.setCalculate(taxi);
        System.out.println(context.calculate(2));
    }
}
