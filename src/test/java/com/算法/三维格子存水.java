package com.算法;

import org.junit.Test;

public class 三维格子存水 {
    private int[][] array = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};

    @Test
    public void test() {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (i == 0 || i == array.length - 1) {
                System.out.println("边上不算i:" + i);
                continue;
            }
            int[] row = array[i];

            for (int j = 0; j < row.length; j++) {
                if (j == 0 || j == row.length - 1) {
                    System.out.println("边上不算j:" + j);
                    continue;
                }
                int now = row[j];

                int up = getMaxUp(i, j);
                int down = getMaxDown(i);
                int left = getMaxLeft(i);
                int right = getMaxRight(i);
                System.out.println("上墙:" + up + "，下墙:" + down + "，当前:" + now + "，左墙：" + left + "，右墙：" + right);
                if (up > now && down > now && left > now && right > now) {
                    sum += Math.min(Math.min(Math.min(up, down), left), right) - now;
                }
            }
        }
        System.out.println("储存水的高度:" + sum);
    }

    private int getMaxUp(int i, int j) {
        int max = 0;
        for (int l = i - 1; l >= 0; l--) {
            max = Math.max(max, array[l][j]);
        }

        return max;
    }

    private int getMaxDown(int i) {
        return 0;
    }

    private int getMaxLeft(int i) {
        return 0;
    }

    private int getMaxRight(int i) {
        return 0;
    }
}
