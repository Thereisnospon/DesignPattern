package adapter;

/**
 * Created by yzr on 16/8/17.
 */

/**
 * Target
 */
interface IPlus{
    int add(int a,int b);
}

/**
 * Adaptee
 */
class Calculate{
    int calculate(int a,int b,char op){
        switch (op){
            case '+':return a+b;
            case '-':return a-b;
            case '*':return a*b;
            case '/':return a/b;
        }
        throw new IllegalArgumentException();
    }
}

/**
 * Class Adapter
 */
class PlusAdapter extends Calculate implements IPlus{
    @Override
    public int add(int a, int b) {
        return calculate(a,b,'+');
    }
}

/**
 * Object Adapter
 */
class ObjectAdapter implements IPlus{


    Calculate mCalculate;

    ObjectAdapter(Calculate calculate){
        this.mCalculate=calculate;
    }

    @Override
    public int add(int a, int b) {
        return mCalculate.calculate(a,b,'+');
    }
}
public class AdapterTest {

    public static void main(String []args){
        IPlus plus=new PlusAdapter();
        IPlus oplus=new ObjectAdapter(new Calculate());
        System.out.println(plus.add(1,2));
        System.out.println(oplus.add(2,3));
    }
}
