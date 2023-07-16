package com.mango.bean;

import java.math.BigInteger;

/**
 * hello spring
 *
 * @author mango
 * @since 2023/7/15
 */
public class SimpleTest {

    public static void main(String[] args) {
        int numRows = 10;
        BigInteger[] prevRow = new BigInteger[] { BigInteger.ONE };

        for (int i = 0; i < numRows; i++) {
            BigInteger[] currRow = new BigInteger[i + 1];
            currRow[0] = currRow[i] = BigInteger.ONE;

            for (int j = 1; j < i; j++) {
                currRow[j] = prevRow[j - 1].add(prevRow[j]);
            }

            // 输出结果到控制台或文件
            for (int j = 0; j <= i; j++) {
                System.out.print(currRow[j] + " ");
            }
            System.out.println();

            // 更新上一行
            prevRow = currRow;
        }

    }

}
