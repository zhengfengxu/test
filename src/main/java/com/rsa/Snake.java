package com.rsa;
/**
 * Created by LIUFANGDA310 on 2018-8-27.
 */
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
/**
 * 多线程版本
 * @author lfd
 *有bug，不完美，好比蛇向左移动时，你让他向右移动，此时会暂停
 *界面刚出来时食物的位置跟你按方向键开始玩时的位置不同
 */
public class Snake {
    private String[][] position = new String[24][30];//游戏界面的的大小
    private volatile LinkedList<String> snakeBody;
    private static final int FOOD_NUM = 10;//默认初始化的食物数量
    private volatile int speed = 600;//刚开始的移动速度
    private static final int INCREASE_SPEED = 15; //每吃一个食物增长的速度
    private volatile String operation;//用户的操做

    public Snake() {
        initWall();
        initSnake();
        initFood(FOOD_NUM);
        print();
    }

    public void move() {
        MoveThread moveThread = new MoveThread();//建立一个线程
        new Thread(moveThread).start();//启动线程
        while(true) {
            Scanner sc = new Scanner(System.in);
            operation = sc.next();//接收用户的输入
            if(operation.length()>=2) {
                operation = operation.substring(0,1);
            }
        }
    }

    //向上移动
    public void moveUp() {
        if((getX(0)-1 +","+ getY(0)).equals(snakeBody.get(1))) {//判断蛇头的方向是不是朝着本身身体的方向
            return;
        }
        if(position[getX(0)-1][getY(0)].equals("■")) {//判断是否是撞墙
            System.out.println("你死了");
            System.exit(0);
        }
        if(position[getX(0)-1][getY(0)].equals("●")) {//判断有没有吃到食物
            snakeBody.addFirst(getX(0)-1+","+getY(0));
            speed -= INCREASE_SPEED;//吃到一个食物，移动速度加快一些
            initFood(1);//产生一个食物
        }else {
            //没有吃到食物也没有撞墙
            snakeBody.addFirst(getX(0)-1+","+getY(0));//添加蛇头
            position[getX(snakeBody.size()-1)][getY(snakeBody.size()-1)] = " ";//移除蛇尾
            snakeBody.removeLast();
        }
        print();//每次移动后都从新打印界面
    }

    //向下移动
    public void moveDown() {
        if((getX(0)+1 +","+ getY(0)).equals(snakeBody.get(1))) {
            return;
        }
        if(position[getX(0)+1][getY(0)].equals("■")) {
            System.out.println("你死了");
            System.exit(0);
        }
        if(position[getX(0)+1][getY(0)].equals("●")) {
            snakeBody.addFirst(getX(0)+1+","+getY(0));
            speed -= INCREASE_SPEED;//吃到一个食物，移动速度加快一些
            initFood(1);//产生一个食物
        }else {
            snakeBody.addFirst(getX(0)+1+","+getY(0));
            position[getX(snakeBody.size()-1)][getY(snakeBody.size()-1)] = " ";
            snakeBody.removeLast();
        }
        print();//每次移动后都从新打印界面
    }

    //向右移动
    public void moveRight() {
        if((getX(0)+ ","+ (getY(0)+1)).equals(snakeBody.get(1))) {
            return;
        }
        if(position[getX(0)][getY(0)+1].equals("■")) {
            System.out.println("你死了");
            System.exit(0);
        }
        if(position[getX(0)][getY(0)+1].equals("●")) {
            snakeBody.addFirst(getX(0)+","+(getY(0)+1));
            speed -= INCREASE_SPEED;//吃到一个食物，移动速度加快一些
            initFood(1);//产生一个食物
        }else {
            snakeBody.addFirst(getX(0)+","+(getY(0)+1));
            position[getX(snakeBody.size()-1)][getY(snakeBody.size()-1)] = " ";
            snakeBody.removeLast();
        }
        print();//每次移动后都从新打印界面
    }

    //向左移动
    public void moveLeft() {
        if((getX(0)+","+ (getY(0)-1)).equals(snakeBody.get(1))) {
            return;
        }
        if(position[getX(0)][getY(0)-1].equals("■")) {
            System.out.println("你死了");
            System.exit(0);
        }
        if(position[getX(0)][getY(0)-1].equals("●")) {
            snakeBody.addFirst(getX(0)+","+(getY(0)-1));
            speed -= INCREASE_SPEED;
            initFood(1);//产生一个食物
        }else {
            snakeBody.addFirst(getX(0)+","+(getY(0)-1));
            position[getX(snakeBody.size()-1)][getY(snakeBody.size()-1)] = " ";
            snakeBody.removeLast();
        }
        print();//每次移动后都从新打印界面
    }

    //获得某节蛇身的x坐标
    public int getX(int index) {
        return Integer.parseInt(snakeBody.get(index).split(",")[0]);
    }

    //获得某节蛇身的y坐标
    public int getY(int index) {
        return Integer.parseInt(snakeBody.get(index).split(",")[1]);
    }

    public void print() {
        for(int i = 0; i< snakeBody.size(); i++) {
            int x = getX(i);
            int y = getY(i);
            if(i==0) {
                position[x][y] = "□"; //蛇头是□
                continue;
            }
            position[x][y] = "■";//身体是■
        }
        for(int i = 0;i<position.length;i++) {
            System.out.println();
            for(int j=0;j<position[i].length;j++) {
                System.out.print(position[i][j]+"  ");
            }
        }
    }

    //初始化墙
    public void initWall() {
        for(int i = 0;i<position.length;i++) {
            for(int j=0;j<position[i].length;j++) {
                position[i][j] = " ";
            }
        }
        //初始化第一列和最后一列
        for(int i=0;i<position.length;i++) {
            position[i][0] = "■";
            position[i][position[0].length-1] = "■";
        }
        //初始化第一行和最后一行
        for(int i=0;i<position[0].length;i++) {
            position[0][i] = "■";
            position[position.length-1][i] = "■";
        }
        //在界面中产生一道墙
        for(int i=0;i<position.length/2;i++) {
            position[(position.length/4)+i][position[0].length/4] = "■";
        }
    }

    //初始化蛇的位置
    public void initSnake() {
        snakeBody = new LinkedList<>();
        snakeBody.add(position.length/2 + "," + (position[0].length/2));
        snakeBody.add(position.length/2 + "," + (position[0].length/2+1));
        snakeBody.add(position.length/2 + "," + (position[0].length/2+2));
        snakeBody.add(position.length/2 + "," + (position[0].length/2+3));
    }

    /**
     * 初始化食物数量
     * @param number 食物数量
     */
    public void initFood(int number) {
        int flag = 0;
        while(flag != number) {
            int x = new Random().nextInt(position.length-1) + 1;
            int y = new Random().nextInt(position[0].length-1) + 1;
            if(position[x][y] == " ") {
                position[x][y] = "●";
                flag++;
            }else {
                continue;
            }
        }
    }

    //实现自动移动的线程类
    class MoveThread implements Runnable{
        @Override
        public void run() {
            while(true) {
                if("w".equals(operation)) {//若用户输入的是w，向上移动
                    moveUp();
                }else if("s".equals(operation)) {//若用户输入的是s，向下移动
                    moveDown();
                }else if("a".equals(operation)) {//若用户输入的是a，向左移动
                    moveLeft();
                }else if("d".equals(operation)) {//若用户输入的是d，向上移动
                    moveRight();
                }else{
                    Thread.yield();//输入除asdw以外的其余字母，暂停移动
                }
                try {
                    Thread.sleep(speed);//每次移动后让线程睡眠一段时间
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}