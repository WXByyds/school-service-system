package controller;

import utils.DruidUtil;

public class ManageController {
    public static void main(String[] args) {
        try {
            DruidUtil.init();
            LoginController.show();
            //MainController.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
