package com.atmianshi.jmm;

/**
 * @program: atguigutwo
 * @description: volatile验证指令禁止重排序
 * @author: mxk
 * @create: 2020-05-31 16:15
 **/
public class VolatileSingleDemo {

    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            new Thread(() -> {
                MySingle.getInstance();
            },""+i).start();
        }
    }

}


class MySingle{
    private static volatile MySingle mySingle=null;

     private MySingle(){
         System.out.println(Thread.currentThread().getName()+"构造开始");
     }

    public static  MySingle getInstance(){
         if(mySingle==null){

             synchronized (MySingle.class){
                 if(mySingle==null){
                     mySingle=new MySingle();
                 }
             }
         }
        return mySingle;
    }
}
