package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShuduUtil {
/*    private static Integer[] a1 = {0,0,0,3,9,0,0,5,0};
    private static Integer[] a2 = {5,0,0,7,0,1,0,0,0};
    private static Integer[] a3 = {7,0,1,0,0,8,0,3,0};
    private static Integer[] a4 = {0,4,0,8,0,0,5,1,0};
    private static Integer[] a5 = {0,0,0,0,0,9,6,0,2};
    private static Integer[] a6 = {6,1,0,0,7,5,0,0,0};
    private static Integer[] a7 = {0,9,3,0,8,0,0,0,0};
    private static Integer[] a8 = {0,2,0,1,5,0,4,0,3};
    private static Integer[] a9 = {0,0,7,0,0,0,0,2,6};*/

    /*private static Integer[] a1 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a2 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a3 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a4 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a5 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a6 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a7 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a8 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a9 = {0,0,0,0,0,0,0,0,0};*/

/*    private static Integer[] a1 = {5,0,0,0,4,0,0,0,0};
    private static Integer[] a2 = {7,0,0,0,2,9,0,0,8};
    private static Integer[] a3 = {0,0,8,1,0,0,9,0,4};
    private static Integer[] a4 = {0,7,0,0,0,8,0,0,6};
    private static Integer[] a5 = {2,0,9,0,0,0,0,0,0};
    private static Integer[] a6 = {0,0,0,0,0,0,0,1,7};
    private static Integer[] a7 = {0,0,5,2,0,0,0,0,0};
    private static Integer[] a8 = {6,0,0,0,0,0,1,0,0};
    private static Integer[] a9 = {0,4,0,0,0,0,7,6,0};*/

    /*private static Integer[] a1 = {5,0,0,0,4,0,0,0,0};
    private static Integer[] a2 = {7,0,0,0,2,9,0,0,8};
    private static Integer[] a3 = {0,0,8,1,0,0,9,0,4};
    private static Integer[] a4 = {0,7,0,0,0,8,0,0,6};
    private static Integer[] a5 = {2,0,9,0,0,0,0,0,0};
    private static Integer[] a6 = {0,0,0,0,0,0,0,1,7};
    private static Integer[] a7 = {0,0,5,2,0,0,0,0,0};
    private static Integer[] a8 = {6,0,0,0,0,0,1,0,0};
    private static Integer[] a9 = {0,4,0,0,0,0,7,0,0};*/

    /*private static Integer[] a1 = {1,2,3,4,5,6,7,8,9};
    private static Integer[] a2 = {2,3,4,5,6,7,8,9,1};
    private static Integer[] a3 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a4 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a5 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a6 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a7 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a8 = {0,0,0,0,0,0,0,0,0};
    private static Integer[] a9 = {0,0,0,0,0,0,0,0,0};*/

    private static Integer[] a1 = {0,0,0,0,0,1,0,0,7};
    private static Integer[] a2 = {0,0,0,0,0,0,5,1,0};
    private static Integer[] a3 = {0,6,0,0,0,0,0,0,9};
    private static Integer[] a4 = {3,0,7,0,0,0,4,8,0};
    private static Integer[] a5 = {0,0,0,9,0,0,0,5,0};
    private static Integer[] a6 = {0,0,0,2,0,0,0,0,0};
    private static Integer[] a7 = {0,0,0,0,2,0,6,4,0};
    private static Integer[] a8 = {7,2,0,0,0,4,1,0,0};
    private static Integer[] a9 = {1,5,0,0,3,0,0,2,0};


    private static Integer[][] a = {a1,a2,a3,a4,a5,a6,a7,a8,a9};


    // c和a映射关系
    private static Map<String,Integer[][]> caM = new HashMap<>();
    private static String[] caS1 = {"0,0","0,1","0,2","1,0","1,1","1,2","2,0","2,1","2,2"};
    private static String[] caS2 = {"0,3","0,4","0,5","1,3","1,4","1,5","2,3","2,4","2,5"};
    private static String[] caS3 = {"0,6","0,7","0,8","1,6","1,7","1,8","2,6","2,7","2,8"};
    private static String[] caS4 = {"3,0","3,1","3,2","4,0","4,1","4,2","5,0","5,1","5,2"};
    private static String[] caS5 = {"3,3","3,4","3,5","4,3","4,4","4,5","5,3","5,4","5,5"};
    private static String[] caS6 = {"3,6","3,7","3,8","4,6","4,7","4,8","5,6","5,7","5,8"};
    private static String[] caS7 = {"6,0","6,1","6,2","7,0","7,1","7,2","8,0","8,1","8,2"};
    private static String[] caS8 = {"6,3","6,4","6,5","7,3","7,4","7,5","8,3","8,4","8,5"};
    private static String[] caS9 = {"6,6","6,7","6,8","7,6","7,7","7,8","8,6","8,7","8,8"};
    private static String[][] caS = {caS1,caS2,caS3,caS4,caS5,caS6,caS7,caS8,caS9};

    private static Integer[][] ca1 = {{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
    private static Integer[][] ca2 = {{0,3},{0,4},{0,5},{1,3},{1,4},{1,5},{2,3},{2,4},{2,5}};
    private static Integer[][] ca3 = {{0,6},{0,7},{0,8},{1,6},{1,7},{1,8},{2,6},{2,7},{2,8}};
    private static Integer[][] ca4 = {{3,0},{3,1},{3,2},{4,0},{4,1},{4,2},{5,0},{5,1},{5,2}};
    private static Integer[][] ca5 = {{3,3},{3,4},{3,5},{4,3},{4,4},{4,5},{5,3},{5,4},{5,5}};
    private static Integer[][] ca6 = {{3,6},{3,7},{3,8},{4,6},{4,7},{4,8},{5,6},{5,7},{5,8}};
    private static Integer[][] ca7 = {{6,0},{6,1},{6,2},{7,0},{7,1},{7,2},{8,0},{8,1},{8,2}};
    private static Integer[][] ca8 = {{6,3},{6,4},{6,5},{7,3},{7,4},{7,5},{8,3},{8,4},{8,5}};
    private static Integer[][] ca9 = {{6,6},{6,7},{6,8},{7,6},{7,7},{7,8},{8,6},{8,7},{8,8}};
    private static Integer[][][] ca = {ca1,ca2,ca3,ca4,ca5,ca6,ca7,ca8,ca9};
    //private static String isTest = "";
    // 初始化ca映射关系
    public static void initCAM(){
        for (int i = 0;i < 9;i ++) {
            for (int j = 0;j < 9;j ++) {
                caM.put(caS[i][j],ca[i]);
            }
        }
    }

    public static void main(String[] args) {
        try {

            initCAM();// 初始化ca映射
            List<Integer[][]> a0List = new ArrayList<>();
            a0List.add(a);
            List<Integer[][]> a1List = trying(a0List,0,a1);
            List<Integer[][]> a2List = trying(a1List,1,a2);
            List<Integer[][]> a3List = trying(a2List,2,a3);
            List<Integer[][]> a4List = trying(a3List,3,a4);
            List<Integer[][]> a5List = trying(a4List,4,a5);
            List<Integer[][]> a6List = trying(a5List,5,a6);
            List<Integer[][]> a7List = trying(a6List,6,a7);
            List<Integer[][]> a8List = trying(a7List,7,a8);
            List<Integer[][]> a9List = trying(a8List,8,a9);
            print("----答案个数："+a9List.size());
            print(a9List);

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    // 每行遍历，
    public static List<Integer[][]> trying(List<Integer[][]> aList,Integer ai,Integer[] arr){
        List<Integer[][]> resultList = new ArrayList<>();// 所有可能性
        List<Integer> intList = new ArrayList<>();// 排除此行已有的数字
        for (int k = 1;k <= 9;k ++) {
            if (!Arrays.asList(arr).contains(k)) {
                intList.add(k);
            }
        }

        List<Integer[]> paramList = new ArrayList<>();// 此行此列所有可能性
        Integer[] arr0 = Arrays.copyOf(arr,arr.length);
        paramList.add(arr0);// 此行原始数字

        //for (Integer[][] a:aList) {
        for (int m = 0;m < aList.size();m ++) {// 遍历上一行所有可能性
            Integer[][] a = aList.get(m);// 上一行情况
            for (int j = 0 ;j < 9;j ++) {// 循环此行9列
                if (arr[j] == 0) {// 如果此位置为0则填充
                    List<Integer[]> intArrList1 = new ArrayList<>();// 此位置可能性初始化
                    for (Integer[] param:paramList) {// 此行现有的数字
                        List<Integer> intList1 = new ArrayList<>();// 此位置剩余可填数字
                        intList1.addAll(intList);
                        intList1.removeAll(getIntList(a,ai,j,Arrays.asList(param)));// 3个规则排除
                        intArrList1.addAll(trying11(param,j,intList1));// 此位置所有可能性
                    }
                    paramList.removeAll(paramList);//
                    paramList.addAll(intArrList1);// 此行可能性-截止到此位置
                }
            }

            for (Integer[] a2:paramList) {// 循环此行可能性
                Integer[][] aNew = Arrays.copyOf(a,a.length);
                aNew[ai] = a2;
                resultList.add(aNew);// 更新到此行为止的可能性
            }
            paramList.clear();// 初始化此行最初值
            paramList.add(arr0);
        }
        return resultList;
    }

    // 根据此位置可能数字，得到此行所有可能情况
    public static List<Integer[]> trying11(Integer[] arr,int j,List<Integer> intList){
        List<Integer[]> resultList = new ArrayList<>();
        for (Integer k:intList) {
            Integer[] arr1 = Arrays.copyOf(arr,arr.length);
            arr1[j] = k;
            resultList.add(arr1);
            //System.out.println( "----arr1="+Arrays.toString(arr1) );
        }
        return resultList;
    }

    public static void print(List<Integer[][]> aList) {
        for (Integer[][] aa:aList) {
            print(aa);
        }

    }

    public static void print(Integer[][] a) {
        for (int i = 0;i < 9;i ++) {
            for (int j = 0;j < 9;j ++) {
                System.out.print( "" + a[i][j] + ",");
            }
            System.out.println( ",");
        }
        System.out.println( ",");
    }

    public static void print(String log) {
        System.out.println(log);
    }

    // 根据规则，获取应该排除的数字
    public static List<Integer> getIntList(Integer[][] a,Integer ai,Integer aj,List<Integer> intList){
        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer> intMap = new HashMap<>();
        // a区去重
        for (Integer integer:intList) {
            intMap.put(integer,integer);
        }
        // b区去重
        for (int l = 0;l < 9;l ++) {
            if (a[l][aj] != 0) {
                intMap.put(a[l][aj],a[l][aj]);
            }
        }
        // c区去重
        Integer[] cn = getC(a,ai,aj);
        for (int i = 0;i < cn.length;i ++) {
            if (cn[i] != 0) {
                intMap.put(cn[i],cn[i]);
            }
        }

        for(Integer key : intMap.keySet()){
            result.add(key);
        }
        return result;
    }

    // 获取当前位置所在的c区
    public static Integer[] getC(Integer[][] a, int ai,int aj){
        Integer[] cn = {0,0,0,0,0,0,0,0,0};
        Integer[][] ca = caM.get(ai + "," + aj);
        for (int i = 0;i < ca.length;i ++) {
            cn[i] = a[ca[i][0]][ca[i][1]];
        }
        return cn;
    }

}
