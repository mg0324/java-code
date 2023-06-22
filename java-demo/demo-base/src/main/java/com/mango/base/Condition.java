package com.mango.base;

public class Condition {
    public static void main(String[] args) {
        int grade = 90;
        if(grade >= 90){
            System.out.println("优");
        }else if(grade >= 80 && grade < 90){
            System.out.println("良");
        }else if(grade >= 60 && grade < 80){
            System.out.println("及格");
        }else {
            System.out.println("不及格");
        }
        
        String sex = "0";
        String sexStr = "";
        switch(sex){
            case "0":
                sexStr = "未知";
                break;
            case "1":
                sexStr = "男";
                break;
            case "2":
                sexStr = "女";
                break;
            default:
                sexStr = "未知";
                break;
        }
        System.out.println(sexStr);
    }
}
