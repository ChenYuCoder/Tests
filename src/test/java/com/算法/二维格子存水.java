package com.算法;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class 二维格子存水 {
    private int[] array = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    private int leftCache = 0;
    private int rightCache = -1;

    @Test
    public void test() {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || i == array.length - 1) {
                System.out.println("边上不算");
                continue;
            }
            int now = array[i];
            int leftMax = getLeftMax(i);
            int rightMax = getRightMax(i);
            System.out.println("左墙:" + leftMax + "，左墙cache:" + getLeftMaxByCache(i) + "，当前:" + now + "，右墙：" + rightMax + "，右墙cache：" + getRightMaxByCache(i));
            //如果当前值小于左右墙，则增加最小高度差
            if (leftMax > now && now < rightMax) {
                sum += Math.min(leftMax, rightMax) - now;
            }
        }
        System.out.println("储存水的高度:" + sum);
    }

    /**
     * 获取右墙
     */
    private int getLeftMax(int i) {
        int max = 0;
        for (int j = i - 1; j >= 0; j--) {
            max = Math.max(max, array[j]);
        }
        return max;
    }

    /**
     * 获取右墙
     */
    private int getLeftMaxByCache(int i) {
        leftCache = Math.max(leftCache, array[i - 1]);
        return leftCache;
    }

    /**
     * 获取左墙
     */
    private int getRightMax(int i) {
        int max = 0;
        for (int j = i + 1; j < array.length; j++) {
            max = Math.max(max, array[j]);
        }
        return max;
    }

    /**
     * 获取左墙
     */
    private int getRightMaxByCache(int i) {
        if (rightCache == -1 || rightCache == array[i]) {
            rightCache = getRightMax(i);
            return rightCache;
        }
        return rightCache;
    }
}
