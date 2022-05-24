package com.lxi;

import java.io.BufferedReader;

import java.io.InputStreamReader;


public class Rpg {

    @SuppressWarnings("unchecked")

    public static void main(String[] args) throws Exception {

        //System.out.println("在这里输入两个人物进行PK,以英文逗号分隔： [BM，DH，MK]");
        System.out.println("----------------开始-------------");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Class c1;

        Class c2;

        try {

            String temp = br.readLine();

            String[] str = temp.split(",");

            if (str.length != 2) {

                throw new Exception("输入格式有误，按默认PK");

            }

            c1 = (Class) Class.forName("com.lxi."

                    + str[0].toUpperCase());

            c2 = (Class) Class.forName("com.lxi."

                    + str[1].toUpperCase());

        } catch (Exception e) {

// TODO Auto-generated catch block

            c1 = (Class) Class.forName("com.lxi.BM");

            c2 = (Class) Class.forName("com.lxi.DH");

        }

        try {

            Person p1 = (Person) c1.newInstance();

            Person p2 = (Person) c2.newInstance();

            long time = System.currentTimeMillis();

            long nextTime1 = (long) (time + p1.coldTime*1000); //

            long nextTime2 = (long) (time + p2.coldTime*1000); //发动攻击的时间

            System.out.println("---游戏开始---");

            while (true) {

                long currenTime = System.currentTimeMillis();

                if (nextTime1 < currenTime) { //时间到则发动攻击

                    p1.hit(p2);

                    nextTime1 += p1.coldTime*1000 + p1.waitTime*1000; //下次攻击时间=冷却时间+被晕眩时间

                    p1.waitTime = 0; //回合结束，重置被晕眩时间为0

                }

                if (nextTime2 < currenTime) {

                    p2.hit(p1);

                    nextTime2 += p2.coldTime*1000 + p2.waitTime*1000;

                    p2.waitTime = 0;

                }

            }

        } catch (ClassCastException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        } catch (InstantiationException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        } catch (IllegalAccessException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
