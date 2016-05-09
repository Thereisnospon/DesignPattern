/**
 * Created by yzr on 16/5/9.
 */

/**
 * 抽象产品:CPU
 */
interface  ICpu{
    void calculate();
}

/**
 * 抽象产品:MainBord
 */
interface  IMainBord{
    void  work();
}

/**
 * 抽象工厂
 */
interface  IFactory{
    ICpu createCpu();
    IMainBord createMainBord();
}

/**
 * 具体产品: IntelCpu
 * 产品族: Intel
 * 产品等级结构: CPU
 */
class  IntelCpu implements  ICpu{
    @Override
    public void calculate() {
        System.out.println("intel cpu");
    }
}

/**
 * 具体产品: AmdCpu
 * 产品族: Amd
 * 产品等级结构: CPU
 */
class  AmdCpu implements  ICpu{

    @Override
    public void calculate() {
        System.out.println("amd cpu");
    }
}

/**
 * 具体产品: IntelMainBord
 * 产品族: Intel
 * 产品等级结构: MainBord
 */
class  IntelMainBord implements  IMainBord{
    @Override
    public void work() {
        System.out.println("intel mainbord");
    }
}

/**
 *具体产品: AmdMainBord
 * 产品族: Amd
 * 产品等级结构: MainBord
 */

class  AmdMainBord implements  IMainBord{
    @Override
    public void work() {
        System.out.println("amd mainbord");
    }
}

/**
 * 具体工厂: Intel
 * 负责生产 Intel 族的产品
 */
class  IntelFactory implements  IFactory{
    @Override
    public ICpu createCpu() {
        return new IntelCpu();
    }

    @Override
    public IMainBord createMainBord() {
        return new IntelMainBord();
    }
}
/**
 * 具体工厂: Amd
 * 负责生产 Amd 族的产品
 */
class  AmdFactory implements  IFactory{
    @Override
    public ICpu createCpu() {
        return  new AmdCpu();
    }

    @Override
    public IMainBord createMainBord() {
        return  new AmdMainBord();
    }
}
/**
 * 生产同一个产品族(同一个厂商)生产的产品进行工作
 */
class  ComputerEnginner {

    public  static void createComputer(int type){
        IFactory factory=null;
        switch (type)
        {
            case 1:
                factory=new IntelFactory();
                break;
            case 2:
                factory=new AmdFactory();
                break;
            default:
                break;
        }
        if(factory!=null){
            ICpu cpu=factory.createCpu();
            IMainBord mainBord=factory.createMainBord();
            cpu.calculate();
            mainBord.work();
        }
    }
}
public class AbstractFactoryDemo {
    public  static  void main(String []args){
        ComputerEnginner computerEnginner=new ComputerEnginner();
        computerEnginner.createComputer(1);
        computerEnginner.createComputer(2);
    }
}
