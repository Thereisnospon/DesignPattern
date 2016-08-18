package iterator;

/**
 * Created by yzr on 16/8/18.
 */


interface Iterator<T>{
    boolean hasNext();
    T next();
}
class StringIterator implements Iterator<String> {

    String strs[];
    int pos=0;

    StringIterator(String []strs){
        this.strs=strs;
        this.pos=0;
    }
    
    @Override
    public boolean hasNext() {
        return strs!=null&&pos<strs.length;
    }

    @Override
    public String next() {
        String item=strs[pos++];
        return item;
    }
}


interface  Aggragate<T>{
    void add(T t);
    Iterator<T>first();
}

class StringAggregate implements Aggragate<String>{

    public static final int MAX=3;

    String []strings;

    int pos=0;

    StringAggregate(){
        strings=new String[MAX];
        pos=0;
        add("c");
        add("java");
        add("python");
    }


    @Override
    public void add(String s) {
        if(pos<strings.length){
            strings[pos++]=s;
        }else{
            System.out.println("fulll !");
        }
    }

    @Override
    public Iterator<String> first() {
        return new StringIterator(strings);
    }
}
public class IteratorDemo {

    public static void main(String []args){

        StringAggregate aggregate=new StringAggregate();
        Iterator<String> iterator=aggregate.first();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
