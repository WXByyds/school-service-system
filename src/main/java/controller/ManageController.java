package controller;

import utils.DruidUtil;

/**
 * @author wxb
 * @version 1.0
 * 程序入口，进入登录界面
 * 调用工具类的方法，初始化连接池的配置信息
 */
public class ManageController {
    public static void main(String[] args) {
        try {
            DruidUtil.init();
            LoginController.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
