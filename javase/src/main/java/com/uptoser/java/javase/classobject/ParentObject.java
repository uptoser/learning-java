package com.uptoser.java.javase.classobject;

/*
父类
 */
public class ParentObject {
    protected String name = "parent";
    private String _private = "private";//私有的变量

    public void work() {

    }
    public void say(){
        System.out.println("我是父亲");
    }


    /*
    定义一个内部类类继承父类
     */
    public class ChildrenObject extends ParentObject{

        /*
        重写父类work方法
         */
        @Override
        public void work() {
//            String _private = this._private;//不能访问父亲私有变量,因为变量没有被继承
            String p_private = super._private;
            String name = this.name;//可以访问父类的name变量
            System.out.println(_private);
        }
        /*
        重写父类say方法
         */
        @Override
        public void say(){
            System.out.println("我是儿子");
        }

        public ChildrenObject getChild(){
            work();
            say();
            return this;
        }

        public void getParent(){
            super.say();
        }
    }

    public static void main(String[] args) {
        new ParentObject().new ChildrenObject().getChild().getParent();
    }

}


