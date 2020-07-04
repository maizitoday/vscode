package com.chatroom.clink.utils;
/*
 * @Description: 请输入....
 * @Author: 麦子
 * @Date: 2020-01-15 17:56:59
 * @LastEditTime: 2020-01-15 18:00:01
 * @LastEditors: 麦子
 */

import java.io.Closeable;
import java.io.IOException;

public class CloseUtils {
    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
