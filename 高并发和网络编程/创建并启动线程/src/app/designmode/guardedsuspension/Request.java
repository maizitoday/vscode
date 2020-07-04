package app.designmode.guardedsuspension;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2019-12-05 15:03:20
 * @LastEditTime: 2019-12-05 15:04:04
 * @LastEditors: 麦子
 */

/**
 * 虽说是请求，由于只是用于表示clientThread传递给ServerThread的实例，所以不提供什么特殊的处理
 */
public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Request{" + "name='" + name + '\'' + '}';
    }
}
