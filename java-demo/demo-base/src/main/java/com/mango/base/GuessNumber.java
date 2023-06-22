package com.mango.base;

import java.util.Random;
import java.util.Scanner;

/**
 * 猜字游戏
 */
public class GuessNumber {
    public static void main(String[] args) {
        // 生成结果数
        int resultNumber = new Random().nextInt(100);
        Scanner scanner = new Scanner(System.in);
        boolean over = false;
        while(!over){
            System.out.print("请输入一个数字：");
            String input = scanner.nextLine();
            try{
                int guess = Integer.parseInt(input.trim());
                if(guess == resultNumber){
                    // 猜中
                    over = true;
                }else if(guess < resultNumber){
                    System.out.println("对不起，您猜的数字小啦！");
                }else{
                    System.out.println("对不起，您猜的数字大啦！");
                }
            }catch(NumberFormatException e){
                System.out.println("只能输入数字！");
                continue;
            }
        }
        System.out.println("恭喜您，猜中了！");
        scanner.close();
    }
}
