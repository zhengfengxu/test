package com.rsa;

import java.util.Scanner;

public class game1 {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        int number = (int) (Math.random() * 100 + 1);
        int a;
        int count = 0;
        int max = 100, min = 0;
        System.out.println("输入0-100的整数");
        do {
            a = in.nextInt();
            count = count + 1;
            if(a>max|a<min){
                System.out.println("请输入区间"+min+"到"+max+"的数字");

            }else{
                if (a > number) {
                    max = a;

                    System.out.println("偏大");
                }

                if (a < number) {

                    min = a;
                    System.out.println("偏小");
                }

            }


            System.out.println("区间:" + min + "--" + max);


        } while (a != number);

        System.out.println("爆炸！！！");
    }

}

