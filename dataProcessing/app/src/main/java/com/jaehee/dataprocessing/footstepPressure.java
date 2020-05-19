package com.jaehee.dataprocessing;
import java.util.ArrayList;
import java.util.Arrays;

class returnDataType{
    public float [][]pressure;
    public int cnt;
    public int [][]sum;
}
public class footstepPressure {
    ArrayList<ArrayList<Integer>> left;
    ArrayList<ArrayList<Integer>> right;
    int cnt;
    int len_standard;
    ArrayList<Integer> left_sum;
    ArrayList<Integer> right_sum;
    public footstepPressure(){
        cnt=0;
        len_standard=10;
        left=new ArrayList<ArrayList<Integer>>();
        right=new ArrayList<ArrayList<Integer>>();
        left_sum=new ArrayList<Integer>();
        right_sum=new ArrayList<Integer>();
    }
    public void process(footData[] d){
        Arrays.sort(d);
        int [][]p;
        ArrayList<Integer> tmp_l=new ArrayList<Integer>();
        ArrayList<Integer> tmp_r=new ArrayList<Integer>();
        boolean l_zero=true, r_zero=true;
        for(footData i : d){
            p=i.getPressure();
            left_sum.add( p[0][0] + p[0][1] + p[0][2] + p[0][3] + p[0][4] + p[0][5] +
                    p[0][6] + p[0][7] + p[0][8] + p[0][9] + p[0][10] + p[0][11]);
            if((int)left_sum.get((left_sum.size()-1))!=0){
                l_zero=false;
                for(int k=0;k<12;k++) {
                    tmp_l.add(p[0][k]);
                }
            }
            else{
                if(l_zero) continue;
                //tmp_l.add(Arrays.asList(p));
                left.add((ArrayList<Integer>) tmp_l.clone());
                tmp_l.clear();
                l_zero=true;
                cnt++;
            }
            right_sum.add( p[1][0] + p[1][1] + p[1][2] + p[1][3] + p[1][4] + p[1][5] +
                    p[1][6] + p[1][7] + p[1][8] + p[1][9] + p[1][10] + p[1][11]);
            if((int)right_sum.get((right_sum.size()-1))!=0){
                r_zero=false;
                for(int k=0;k<12;k++) {
                    tmp_r.add(p[1][k]);
                }
            }
            else{
                if(r_zero) continue;
                //tmp_r.add(Arrays.asList(p));
                right.add((ArrayList<Integer>) tmp_r.clone());
                tmp_r.clear();
                r_zero=true;
                cnt++;
            }
        }
        tmp_l.clear();
        tmp_r.clear();

    }
    private void length_normalize(){
        int start[]={0,1,2,3,4,5,6,7,8,9,10,11};
        int sum[]={0,0,0,0,0,0,0,0,0,0,0,0};
        int len=0;
        ArrayList<ArrayList<Integer>> tmp=new ArrayList<ArrayList<Integer>>();
        ArrayList ary=new ArrayList();
        for(ArrayList<Integer> i : left){
            len=i.size()/12;
            //System.out.println(len);
            if(len==len_standard) tmp.add((ArrayList<Integer>)i.clone());
            else if(len>len_standard){
                int x=len/len_standard;
                int y=len%len_standard;
                int check=0;
                start[0]=0; start[1]=1; start[2]=2; start[3]=3; start[4]=4; start[5]=5;start[6]=6;start[7]=7; start[8]=8; start[9]=9; start[10]=10; start[11]=11;
                for(int index=0;index<len_standard;index++){
                    sum[0]=0; sum[1]=0; sum[2]=0; sum[3]=0; sum[4]=0; sum[5]=0;sum[6]=0;sum[7]=0; sum[8]=0; sum[9]=0; sum[10]=0; sum[11]=0;
                    if(y==check){
                        for(int k=0;k<x;k++){
                            sum[0]+=i.get(start[0]); start[0]+=12; sum[1]+=i.get(start[1]); start[1]+=12; sum[2]+=i.get(start[2]); start[2]+=12;
                            sum[3]+=i.get(start[3]); start[3]+=12; sum[4]+=i.get(start[4]); start[4]+=12; sum[5]+=i.get(start[5]); start[5]+=12;
                            sum[6]+=i.get(start[6]); start[6]+=12; sum[7]+=i.get(start[7]); start[7]+=12; sum[8]+=i.get(start[8]); start[8]+=12;
                            sum[9]+=i.get(start[9]); start[9]+=12; sum[10]+=i.get(start[10]); start[10]+=12; sum[11]+=i.get(start[11]); start[11]+=12;
                        }
                        ary.add(sum[0]/x); ary.add(sum[1]/x); ary.add(sum[2]/x); ary.add(sum[3]/x); ary.add(sum[4]/x); ary.add(sum[5]/x);
                        ary.add(sum[6]/x); ary.add(sum[7]/x); ary.add(sum[8]/x); ary.add(sum[9]/x); ary.add(sum[10]/x); ary.add(sum[11]/x);

                    }
                    else{
                        for(int k=0;k<=x;k++){
                            sum[0]+=i.get(start[0]); start[0]+=12; sum[1]+=i.get(start[1]); start[1]+=12; sum[2]+=i.get(start[2]); start[2]+=12;
                            sum[3]+=i.get(start[3]); start[3]+=12; sum[4]+=i.get(start[4]); start[4]+=12; sum[5]+=i.get(start[5]); start[5]+=12;
                            sum[6]+=i.get(start[6]); start[6]+=12; sum[7]+=i.get(start[7]); start[7]+=12; sum[8]+=i.get(start[8]); start[8]+=12;
                            sum[9]+=i.get(start[9]); start[9]+=12; sum[10]+=i.get(start[10]); start[10]+=12; sum[11]+=i.get(start[11]); start[11]+=12;
                        }
                        ary.add(sum[0]/(x+1)); ary.add(sum[1]/(x+1)); ary.add(sum[2]/(x+1)); ary.add(sum[3]/(x+1)); ary.add(sum[4]/(x+1)); ary.add(sum[5]/(x+1));
                        ary.add(sum[6]/(x+1)); ary.add(sum[7]/(x+1)); ary.add(sum[8]/(x+1)); ary.add(sum[9]/(x+1)); ary.add(sum[10]/(x+1)); ary.add(sum[11]/(x+1));
                        check++;
                    }
                }
                tmp.add((ArrayList<Integer>)ary.clone());
                ary.clear();
            }
        }
        left.clear();
        left.addAll(tmp);
        tmp.clear();

        for(ArrayList<Integer> i : right){
            len=i.size()/12;
            if(len==len_standard) tmp.add((ArrayList<Integer>)i.clone());
            else if(len>len_standard){
                int x=len/len_standard;
                int y=len%len_standard;
                int check=0;
                start[0]=0; start[1]=1; start[2]=2; start[3]=3; start[4]=4; start[5]=5;start[6]=6;start[7]=7; start[8]=8; start[9]=9; start[10]=10; start[11]=11;
                for(int index=0;index<len_standard;index++){
                    sum[0]=0; sum[1]=0; sum[2]=0; sum[3]=0; sum[4]=0; sum[5]=0;sum[6]=0;sum[7]=0; sum[8]=0; sum[9]=0; sum[10]=0; sum[11]=0;

                    if(y==check){
                        for(int k=0;k<x;k++){
                            sum[0]+=i.get(start[0]); start[0]+=12; sum[1]+=i.get(start[1]); start[1]+=12; sum[2]+=i.get(start[2]); start[2]+=12;
                            sum[3]+=i.get(start[3]); start[3]+=12; sum[4]+=i.get(start[4]); start[4]+=12; sum[5]+=i.get(start[5]); start[5]+=12;
                            sum[6]+=i.get(start[6]); start[6]+=12; sum[7]+=i.get(start[7]); start[7]+=12; sum[8]+=i.get(start[8]); start[8]+=12;
                            sum[9]+=i.get(start[9]); start[9]+=12; sum[10]+=i.get(start[10]); start[10]+=12; sum[11]+=i.get(start[11]); start[11]+=12;
                        }
                        ary.add(sum[0]/x); ary.add(sum[1]/x); ary.add(sum[2]/x); ary.add(sum[3]/x); ary.add(sum[4]/x); ary.add(sum[5]/x);
                        ary.add(sum[6]/x); ary.add(sum[7]/x); ary.add(sum[8]/x); ary.add(sum[9]/x); ary.add(sum[10]/x); ary.add(sum[11]/x);
                    }
                    else{
                        for(int k=0;k<= x;k++){
                            sum[0]+=i.get(start[0]); start[0]+=12; sum[1]+=i.get(start[1]); start[1]+=12; sum[2]+=i.get(start[2]); start[2]+=12;
                            sum[3]+=i.get(start[3]); start[3]+=12; sum[4]+=i.get(start[4]); start[4]+=12; sum[5]+=i.get(start[5]); start[5]+=12;
                            sum[6]+=i.get(start[6]); start[6]+=12; sum[7]+=i.get(start[7]); start[7]+=12; sum[8]+=i.get(start[8]); start[8]+=12;
                            sum[9]+=i.get(start[9]); start[9]+=12; sum[10]+=i.get(start[10]); start[10]+=12; sum[11]+=i.get(start[11]); start[11]+=12;
                        }
                        ary.add(sum[0]/(x+1)); ary.add(sum[1]/(x+1)); ary.add(sum[2]/(x+1)); ary.add(sum[3]/(x+1)); ary.add(sum[4]/(x+1)); ary.add(sum[5]/(x+1));
                        ary.add(sum[6]/(x+1)); ary.add(sum[7]/(x+1)); ary.add(sum[8]/(x+1)); ary.add(sum[9]/(x+1)); ary.add(sum[10]/(x+1)); ary.add(sum[11]/(x+1));
                        check++;
                    }
                }
                tmp.add((ArrayList<Integer>)ary.clone());
                ary.clear();
            }
        }
        right.clear();
        right.addAll(tmp);
        tmp.clear();
    }

    private float[][] featureExtract(){
        int l_sum[]=new int[12*len_standard];
        int r_sum[]=new int[12*len_standard];
        float [][]pressure=new float[2][12*len_standard];
        int len=left.size()<right.size()?left.size():right.size();
        System.out.println(len);
        for(int i=0;i<len;i++){
            for(int j=0;j<120;j++)
            {
                l_sum[j]+=left.get(i).get(j);
                r_sum[j]+=right.get(i).get(j);
            }
        }
        for(int j=0;j<120;j++)
        {
            pressure[0][j]=l_sum[j]/(float)len;
            pressure[1][j]=r_sum[j]/(float)len;
        }
        left.clear();
        right.clear();
        return pressure;
    }
    public returnDataType getResult() {
        length_normalize();
        returnDataType T = new returnDataType();
        T.cnt = cnt;
        T.pressure = featureExtract();
        T.sum=new int[2][];
        T.sum[0]=new int[left_sum.size()];
        T.sum[1]=new int[right_sum.size()];
        for(int i=0;i<left_sum.size();i++){
            T.sum[0][i]=(int)left_sum.get(i);
        }
        for(int i=0;i<right_sum.size();i++){
            T.sum[1][i]=(int)right_sum.get(i);
        }
        return T;
    }
}