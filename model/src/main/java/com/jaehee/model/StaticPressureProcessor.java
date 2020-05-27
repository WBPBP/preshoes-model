package com.jaehee.model;

public class StaticPressureProcessor {
    private int[][] pressure_part;
    private double[] diff_part;
    private double heelPressureDifference;
    private int len;

    public StaticPressureProcessor(){
        heelPressureDifference = 0;
        len = 0;
        diff_part = new double[3];
        pressure_part = new int[2][4];
    }

    /**
     *  FootData 배열을 입력받아 각 발의 모든 센서를 합하여 배열에 저장
     * @param d 정지한 경우, 센서로부터 입력받은 데이터 배열
     */
    public  void process(FootData[]d) {
        for (FootData i : d) {
            int[][] p = i.getPressure();
            pressure_part[0][0] += p[0][0];
            pressure_part[0][1] += p[0][1] + p[0][2] + p[0][3] + p[0][4];
            pressure_part[0][2] += p[0][5] + p[0][6] + p[0][7];
            pressure_part[0][3] += p[0][8] + p[0][9] + p[0][10] + p[0][11];
            pressure_part[1][0] += p[1][0];
            pressure_part[1][1] += p[1][1] + p[1][2] + p[1][3] + p[1][4];
            pressure_part[1][2] += p[1][5] + p[1][6] + p[1][7];
            pressure_part[1][3] += p[1][8] + p[1][9] + p[1][10] + p[1][11];
            len++;
        }
    }

    private void calculatePartPressureDifference(){
        pressure_part[0][0] /= len; pressure_part[0][1] /= len; pressure_part[0][2] /= len; pressure_part[0][3] /= len;
        pressure_part[1][0] /= len; pressure_part[1][1] /= len; pressure_part[1][2] /= len; pressure_part[1][3] /= len;
        diff_part[1] = pressure_part[1][3] - pressure_part[1][1];
        diff_part[0] = pressure_part[0][3] - pressure_part[0][1];
        diff_part[0] = (diff_part[0] + 60)/120.0;
        diff_part[1] = (diff_part[1] + 60)/120.0;
    }

    private void calculatePressureDifference(){
        diff_part[2] = (pressure_part[1][0] + pressure_part[1][1] + pressure_part[1][2] + pressure_part[1][3]) - (pressure_part[0][0] + pressure_part[0][1] + pressure_part[0][2] + pressure_part[0][3]);
        diff_part[2] = (diff_part[2] + 180)/360.0;
    }

    private void calculateheelPressureDifferencee(){
        heelPressureDifference = Math.abs(pressure_part[1][3] - pressure_part[0][3]);
    }

    /**
     * 정적 족저압의 결과 값 가공하여 리턴
     * @return res [왼발의 앞/뒤꿈치 차이(0(앞)~1(뒤)),왼발의 앞/뒤꿈치 차이(0(앞)~1(뒤)), 두 발의 압력 차 (0(왼)~1(오)), 양 발 뒤꿈치 차이 평균]
     */
    public double[] getResult(){
        calculatePartPressureDifference();
        calculatePressureDifference();
        calculateheelPressureDifferencee();
        double[] res ={diff_part[0], diff_part[1], diff_part[2], heelPressureDifference };
        return res;
    }
}
