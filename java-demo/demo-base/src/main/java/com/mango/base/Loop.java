package com.mango.base;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Loop {
    private static IntStream range;

    public static void main(String[] args) {
        // int count = 5;
        // do{
        //     System.out.println(count--);
        // }while(count>0);
        // 5 4 3 2 1


        // int count = 5;
        // while(count>0){
        //     System.out.println(count--);
        // }
        // 5 4 3 2 1

        for(int count=5;count>0;count--){
            System.out.println(count);
        }
        // 5 4 3 2 1

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        for(Integer n : list){
            System.out.println(n);
        }

        list.forEach(e -> {
            System.out.println(e);
        });

        
    }
}
