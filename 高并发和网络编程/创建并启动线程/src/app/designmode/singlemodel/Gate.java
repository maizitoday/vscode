package app.designmode.singlemodel;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-03 17:57:56
 * @LastEditTime: 2019-12-04 11:27:33
 * @LastEditors: 麦子
 */

public class Gate {
    private int counter;
    private String name = "Nobody";
    private String address = "Nowhere";

    // 临界值
    // 门里面的一个方法
    public synchronized void pass(String name, String address) {
        this.counter++;
        // 因为他是公共资源， 在这个地方赋值的时候， this.name 和 this.address 值有可能被其他线程覆盖。
        this.name = name;
        this.address = address;
        verify();
    }

    // 通过的方法
    private void verify() {
        if (this.name.contains(this.address) != true) {
            System.out.println("********BROKE----->" + this.name+"-->"+this.address);
        }
    }
}