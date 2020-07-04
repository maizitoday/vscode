package app.designmode.balking;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 17:45:22
 * @LastEditTime: 2019-12-06 10:31:37
 * @LastEditors: 麦子
 */

public class Customer {
    private volatile boolean m_calledService = false;// 取值为false表示没有服务请求，否则有服务请求。
    private String m_Name;

    public Customer(String name) {

        this.m_Name = name;

    }

    /**
     * 获得服务的方法,如果警戒条件不成立，那么直接返回，不再执行操作
     */

    public synchronized boolean GetService(String waiterName, String serviceName) {
        if (!m_calledService) {

            System.out.println("no service  need----------------------------------------------");

            return false;

        }

        System.out.println(waiterName + "提供服务-- " + serviceName);
        m_calledService = false;
        return true;

    }

    /**
     * 顾客向服务员发出服务请求,当顾客已经是发出服务要求的状态，那么没有必要,执行更改状态的操作，直接返回false,所以，这也是一个Balking Pattern的设计
     *      
     */

    public synchronized boolean CallService(String serviceName) {

        if (m_calledService) {

            System.out.println("not get service----------------------------------------------");

            return false;

        }

        m_calledService = true;

        System.out.println(m_Name + "需要服务 : " + serviceName);

        return true;

    }

}