package clone;

/**
 * Created by yzr on 16/8/18.
 */


class Address implements Cloneable{

    String country;
    String city;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Address address=(Address) super.clone();
        address.country=this.country;
        address.city=this.city;
        return address;
    }
}
class Student implements Cloneable{
    String name;
    Address address;

    @Override
    protected Object clone()  {
        Student student=null;
        try{
            student=(Student)super.clone();
            student.address=(Address) this.address.clone();
            student.name=this.name;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return student;
    }
}


public class CloneDemo {
    public static void main(String []args){

    }
}
