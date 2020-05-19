package com.jaehee.dataprocessing;

import java.util.Arrays;
public class staticPressure {
    int[][] pressure;
    int[][] p;
    public  void process(footData []d) {
        Arrays.sort(d);
        pressure=new int[2][d.length];
        int l=0, r=0;
        for (footData i : d) {
            p=i.getPressure();
            pressure[0][l++]=( p[0][0] + p[0][1] + p[0][2] + p[0][3] + p[0][4] + p[0][5] +
                    p[0][6] + p[0][7] + p[0][8] + p[0][9] + p[0][10] + p[0][11]);
            pressure[1][r++]=(p[1][0] + p[1][1] + p[1][2] + p[1][3] + p[1][4] + p[1][5] +
                    p[1][6] + p[1][7] + p[1][8] + p[1][9] + p[1][10] + p[1][11]);
        }
    }
    public int[][] getResult(){
        return pressure;
    }
}
