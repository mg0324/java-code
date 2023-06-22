package com.mango.base.oop;

import java.util.Random;
import java.util.Scanner;

/**
 * 游戏类
 */
public class Game {
    private int result;
    private Scanner scanner;

    public Game(){
        // 初始化随机结果数
        this.result = new Random().nextInt(100);
        this.scanner = new Scanner(System.in);
    }

    public int getResult() {
        return result;
    }

    public Scanner getScanner(){
        return scanner;
    }
    /**
     * 退出游戏
     */
    public void exit(){
        this.scanner.close();
        System.out.println("游戏退出！");
    }

}
