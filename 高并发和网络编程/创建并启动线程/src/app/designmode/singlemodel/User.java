package app.designmode.singlemodel;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-03 17:55:50
 * @LastEditTime: 2019-12-04 11:05:53
 * @LastEditors: 麦子
 */

public class User implements Runnable {

    private final String myname;
    private final String myAddress;
    public Gate gate;
     

    public User(String myname, String myAddress, Gate gate) {
        this.myname = myname;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(myname+"-->BEGEN***");
        while (true) {
            this.gate.pass(this.myname,this.myAddress);
        }
    }

    public static void main(String[] args) {

        Gate gute = new Gate();

        User bj =new User("小A","A",gute);
        User sh =new User("小B","B",gute);
        User sx =new User("小C","C",gute);
    

        Thread t_A = new Thread(bj);
        Thread t_B = new Thread(sh);
        Thread t_C = new Thread(sx);

        t_A.start();
        t_B.start();
        t_C.start();

    }

}