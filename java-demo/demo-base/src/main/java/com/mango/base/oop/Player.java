package com.mango.base.oop;

import java.util.Scanner;

/**
 * 玩家类
 */
public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    /**
     * 玩游戏
     * @param game 游戏
     */
    public void play(Game game){
        Scanner scanner = game.getScanner();
        boolean over = false;
        int resultNumber = game.getResult();
        while(!over){
            System.out.print("["+name+"]请输入一个数字：");
            String input = scanner.nextLine();
            try{
                int guess = Integer.parseInt(input.trim());
                if(guess == resultNumber){
                    // 猜中
                    over = true;
                }else if(guess < resultNumber){
                    System.out.println("对不起，["+name+"]您猜的数字小啦！");
                }else{
                    System.out.println("对不起，["+name+"]您猜的数字大啦！");
                }
            }catch(NumberFormatException e){
                System.out.println("只能输入数字！");
                continue;
            }
        }
        System.out.println("恭喜您["+name+"]，猜中了！");
    }
}
