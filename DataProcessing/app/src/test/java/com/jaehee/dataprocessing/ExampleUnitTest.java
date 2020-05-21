package com.jaehee.dataprocessing;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;
import java.util.Arrays;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void staticPressureTest() {
        //random data produce
        Random random=new Random();
        FootData []d= new FootData[140];
        int index=0;
        int[]l_pressure=new int[12];
        int[] r_pressure=new int[12];
        for(index=0;index<d.length;index++) {
            d[index] = new FootData();
            for (int j = 0; j < 12; j++) {
                l_pressure[j] = random.nextInt(15);
                r_pressure[j] = random.nextInt(15);
            }
            d[index].setData(index, l_pressure, r_pressure);
        }

        //data processing test
        StaticPressure model=new StaticPressure();
        model.process(d);
        int [][]res = model.getResult();
        for(int []i : res){
            for (int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void footstepPressureTest(){
        //random data produce
        Random random=new Random();
        FootData []d= new FootData[1200];
        int[] zero=new int[60];
        for(int i=0;i<zero.length;i++)
            zero[i]=random.nextInt(1200);
        Arrays.sort(zero);
        int z=0;
        int []l_pressure=new int[12];
        int[] r_pressure=new int[12];
        for(int index=0;index<d.length;index++) {
            for (int j = 0; j < 12; j++) {
                l_pressure[j] = random.nextInt(15);
                r_pressure[j] = random.nextInt(15);
            }
            if( z<60 && index==zero[z]) {
                z++;
                for(int j=0;j<12;j++) {
                    l_pressure[j]=0;
                    r_pressure[j]=0;
                }
            }
            d[index] = new FootData(index, l_pressure, r_pressure);
        }

        //data processing test
        FootStepPressure model=new FootStepPressure();
        model.process(d);
        FootStepPressure.ReturnDataType res=model.getResult();
        System.out.println("Pressure : ");
        for(float[] i : res.pressure){
            for(float j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("step : "+res.cnt);
        System.out.println("sum : ");
        for(int []i : res.sum){
            for (int j : i){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}