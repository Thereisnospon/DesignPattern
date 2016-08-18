package visitor;

import java.util.regex.Pattern;

/**
 * Created by yzr on 16/8/18.
 */

interface Visitor{
    void visitBook(Element book);
    void visitGame(Element game);
}


abstract class Element{
    String name;

    public Element(String name) {
        this.name = name;
    }

    abstract void accept(Visitor visitor);
}
class Book extends Element{
    public Book(String name) {
        super(name);
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visitBook(this);
    }
}

class Game extends Element{

    public Game(String name) {
        super(name);
    }

    @Override
    void accept(Visitor visitor) {
        visitor.visitGame(this);
    }
}
class Student implements Visitor{


    @Override
    public void visitBook(Element book) {
        System.out.println("学生对"+book.name+"的评价为:"+70);
    }

    @Override
    public void visitGame(Element game) {
        System.out.println("学生对"+game.name+"的评价为:"+80);
    }
}
class Parent implements  Visitor{
    @Override
    public void visitBook(Element book) {
        System.out.println("家长对"+book.name+"的评价为"+80);
    }

    @Override
    public void visitGame(Element game) {
        System.out.println("家长对"+game.name+"的评价为"+70);
    }
}
class Teacher implements Visitor{
    @Override
    public void visitBook(Element book) {
        System.out.println("老师对"+book.name+"的评价为"+90);
    }

    @Override
    public void visitGame(Element game) {
        System.out.println("老师对"+game.name+"评价为"+60);
    }
}



public class VisitorDemo {


    public static void main(String []args){
        Book book=new Book("《三国演义》");
        Game game=new Game("真三国无双");


        Student student=new Student();
        Parent parent=new Parent();
        Teacher teacher=new Teacher();

        book.accept(student);
        book.accept(parent);
        book.accept(teacher);

        game.accept(student);
        game.accept(parent);
        game.accept(teacher);
    }
}
