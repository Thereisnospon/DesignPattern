package builder;

/**
 * 省略了抽象建造者和导演
 * 使构造函数私有,只能通过内部的建造者创建
 */
class  Dialog{

    private String title;
    private String msg;
    private String img;

    private Dialog(DialogBuilder builder){
        this.title=builder.title;
        this.msg=builder.msg;
        this.img=builder.img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void show(){
        System.out.printf("Title:%s\tMsg:%s\tImg:%s\n",title,msg,img);
    }


    public static  class  DialogBuilder
    {
        private String title="no title";
        private String msg="no msg";
        private String img="no img";

        DialogBuilder(){

        }

        public DialogBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public DialogBuilder setImg(String img) {
            this.img = img;
            return this;
        }

        public DialogBuilder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Dialog create(){
            return new Dialog(this);
        }
    }
}




public class BuilderDemo {

    public static void main(String []args){
            new Dialog.DialogBuilder()
                    .setTitle("hello")
                    .setMsg("world")
                    .setImg("good")
                    .create()
                    .show();
    }
}
