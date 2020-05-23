package com.jaehee.model;

import java.util.Arrays;

public class StaticPressureProcessor {
    private int[][] pressure;
    private int[] difference;
    private int disease;
    private int standard;

    public StaticPressureProcessor(){ standard = 20; disease = 0; }

    /**
     *  FootData 배열을 입력받아 각 발의 압력 합 계산
     * @param d 정지한 경우, 센서로부터 입력받은 데이터 배열
     */
    public  void process(FootData[]d) {
        Arrays.sort(d);
        pressure = new int[2][d.length];
        difference = new int[d.length];
        int l = 0;
        int cnt = 0;
        for (FootData i : d) {
            int[][] p = i.getPressure();
            pressure[0][l]= (p[0][0] + p[0][1] + p[0][2] + p[0][3] + p[0][4] + p[0][5] +
                    p[0][6] + p[0][7] + p[0][8] + p[0][9] + p[0][10] + p[0][11]);
            pressure[1][l]=(p[1][0] + p[1][1] + p[1][2] + p[1][3] + p[1][4] + p[1][5] +
                    p[1][6] + p[1][7] + p[1][8] + p[1][9] + p[1][10] + p[1][11]);
            difference[l] = pressure[1][l] - pressure[0][l];
            if(Math.abs(difference[l]) > standard) cnt++;
            l++;
        }
        if(cnt > (d.length/2)) disease = 1;
    }

    private double pressureDifference(){
        int sum = 0;
        double avg = 0;
        for(int i : difference){
            sum+= i;
        }
        avg = (double)sum/difference.length;
        return ((avg + 180)/360.0);
    }

    /**
     * 정적 족저압의 결과 값 가공하여 리턴
     * @return res [두 발의 압력 차 (0(왼)~1(오)), 척추측만증 여부]
     */
    public double[] getResult(){
        double[] res = new double[2];
        res[0] = pressureDifference();
        res[1] = disease;
        return res;
    }
}
