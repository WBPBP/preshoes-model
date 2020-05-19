package com.jaehee.dataprocessing;

public class footData implements Comparable<footData>{
    int num;
    int []l_pressure;
    int []r_pressure;
    public footData(){}
    public footData(int num, int[]l_pressure, int[] r_pressure){
        this.num=num;
        this.l_pressure=l_pressure.clone();
        this.r_pressure=r_pressure.clone();
    }
    public void setData(int num, int[]l_pressure, int[] r_pressure){
        this.num=num;
        this.l_pressure=l_pressure.clone();
        this.r_pressure=r_pressure.clone();
    }

    public int getNum(){
        return num;
    }
    public int[][] getPressure(){
        int [][]res={l_pressure, r_pressure};
        return res;
    }
    public int compareTo(footData d){
        if(this.num<d.num) return -1;
        else if(this.num==d.num) return 0;
        else return 1;
    }
}
