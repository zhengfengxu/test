package com.rsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    String identity[]= {"平民","狼人","平民","预言家","狼人","平民","狼人","女巫","平民","白痴","狼人","猎人"};
    List<String> role=new ArrayList<String>();

    //用于女巫用药的判断
    int arr[]= {0,0};
    boolean antidote=true;//解药
    Boolean poison=true;//毒药

    Scanner scanner=new Scanner(System.in);

    //进入
    public void into() {
        System.out.println("----------欢迎进入狼人杀游戏！----------");
        while(true) {
            System.out.println("请选择您的操作： 1.开始游戏   2.退出");
            int choice=Integer.valueOf(scanner.nextLine());
            switch(choice) {
                case 1:
                    allocation();//角色分配
                    start();//开始游戏
                case 2:
                    break;
                default:
                    continue;
            }
        }
    }

    //开始游戏
    private void start() {
        // TODO Auto-generated method stub
        while(true) {
            int d=0;//死亡人数统计

            //夜间行动
            System.out.println("----------天黑请闭眼，狼人请杀人！---------");
            System.out.println("-----------------狼人回合-----------------");
            System.out.println("狼人请输入要猎杀的玩家：");
            int index=Integer.valueOf(scanner.nextLine());
            String r=role.get(index-1);
            d++;

            //女巫行为，若使用毒药，arr[1]为毒杀玩家编号，使用解药则arr[0]=1,都不使用则均为0
            witch(index);
            if(arr[0]==1) {
                role.add(index-1, r);//重新加到list中
                d--;
            }
            if(arr[1]!=0) {//使用毒药
                if(role.contains("预言家")) {//预言家未死
                    prophet();//预言家行为
                }
                d++;
            }

            //判断游戏是否结束
            if(isEnding())  break;

            prophet();//预言家行为

            //白天行动
            System.out.println("-------------天亮了-------------");
            if(d!=0) {//有人死了
                System.out.println("--------昨晚有"+d+"人死了！--------");
                toSpeak(index,arr[1],d);//玩家发言
                vote();//公投阶段
                if(isEnding())  break;
                showPlayer();
                continue;
            }else {
                System.out.println("----------今晚是平安夜！----------");
                toSpeak(index,arr[1],d);
                vote();//公投阶段
                if(isEnding())  break;
                showPlayer();
                continue;
            }
        }
    }

    //公投阶段
    private void vote() {
        // TODO Auto-generated method stub
        System.out.println("请投票选出一位玩家结束游戏：");
        int choice=Integer.valueOf(scanner.nextLine());

        //判断公投玩家是否是猎人
        if(role.get(choice-1).endsWith("猎人")) {
            hunter();//猎人行为
            role.set(choice-1,"");
        }
        //公投玩家为白痴
        else if(role.get(choice-1).endsWith("白痴")){
            idiot(choice-1);//白痴行为
        }else {
            role.set(choice-1,"");
        }
    }

    //猎人行为
    private void hunter() {
        // TODO Auto-generated method stub
        System.out.println("-----------------猎人回合-----------------");
        System.out.println("是否要猎杀一名玩家？ 1.是   2.否");
        int choice=Integer.valueOf(scanner.nextLine());
        if(choice==1) {
            System.out.println("请输入要猎杀的玩家：");
            int num=Integer.valueOf(scanner.nextLine());
            if(role.get(num-1).endsWith("白痴")) {
                idiot(num);
                return;
            }else {
                System.out.println("死亡玩家发表遗言：");
                System.out.println("玩家"+num+"：");
                String talk=scanner.nextLine();
                role.set(num-1,"");
            }
        }
    }

    //白痴行为
    private void idiot(int i) {
        // TODO Auto-generated method stub
        System.out.println("-----------------白痴回合-----------------");
        System.out.println("是否免疫此次放逐？ 1.是   2.否");
        int choice=Integer.valueOf(scanner.nextLine());
        if(choice==1) {
            return;
        }else {
            System.out.println("死亡玩家发表遗言：");
            System.out.println("玩家"+i+"：");
            String talk=scanner.nextLine();
            role.set(i-1,"");//删除白痴玩家
        }
    }

    //玩家发言
    private void toSpeak(int index, int k,int d) {
        // TODO Auto-generated method stub
        String talk;
        if(d!=0) {
            System.out.println("死亡玩家发表遗言：");
            //狼人所杀玩家
            System.out.println("玩家"+index+"：");
            talk=scanner.nextLine();
            role.set(index-1,"");//将被杀的玩家移除
            //毒药所杀玩家
            if(k!=0) {//使用了毒药
                System.out.println("玩家"+k+"：");
                talk=scanner.nextLine();
                role.set(k-1,"");
            }
        }

        System.out.println("剩余玩家开始发言：");
        int random=1+(int)(Math.random()*(role.size()-1));//取随机数

        for(int i=random;i<role.size();i++) {
            if(role.get(i).equals("")) continue;
            System.out.println("玩家"+(i+1)+":");
            talk=scanner.nextLine();
        }
        for(int i=0;i<random;i++) {
            if(role.get(i).equals("")) continue;
            System.out.println("玩家"+(i+1)+":");
            talk=scanner.nextLine();
        }
    }

    //遍历显示当前存活玩家
    private void showPlayer() {
        // TODO Auto-generated method stub
        System.out.println("目前身份：");
        for(String s:role){
            if(s.equals("")) continue;
            System.out.println(s);
        }
        System.out.println("-----------------------------------------");
        System.out.println();
    }

    //预言家行为
    private void prophet() {
        // TODO Auto-generated method stub
        System.out.println("-----------------预言家回合-----------------");
        System.out.println("是否查验一名玩家身份? 1.是  2.否");
        int choice=Integer.valueOf(scanner.nextLine());
        if(choice==1) {
            System.out.println("请选择要查验的玩家：");
            int index=Integer.valueOf(scanner.nextLine());
            if(role.get(index-1).endsWith("狼人")) {//是狼人
                System.out.println("玩家"+index+"是狼人");
            }else {
                System.out.println("玩家"+index+"是好人");
            }
        }else {
            return;
        }
    }

    //女巫行为
    private void witch(int index) {
        // TODO Auto-generated method stub
        boolean flag=true;//是否使用药剂，使用则为false
        System.out.println("-----------------女巫回合-----------------");
        System.out.println("被杀的是玩家"+index);

        //还有解药能够使用
        if(antidote) {
            System.out.println("是否对玩家"+index+"选择使用解药? 1.使用  2.不使用");
            int choice1=Integer.valueOf(scanner.nextLine());
            if(choice1==1) {//输入除1以外的内容均视为不使用解药
                flag=false;
                antidote=false;//解药不可再次使用
                arr[0]=1;
                return;
            }
        }

        //还有毒药能够使用且未使用解药
        if(poison&&flag) {
            System.out.println("是否选择使用毒药? 1.使用  2.不使用");
            int choice2=Integer.valueOf(scanner.nextLine());
            if(choice2==1) {//输入除1以外的内容均视为不使用毒药
                System.out.println("请选择使用毒药的玩家：");
                int num=Integer.valueOf(scanner.nextLine());
                poison=false;//毒药不可再次使用
                arr[1]=num;
            }
        }
    }

    //游戏结束判定
    private boolean isEnding() {
        // TODO Auto-generated method stub
        int priest=0;//神职人员
        int civilians=0;//平民
        int werewolf=0;//狼人

        for(int i=0;i<role.size();i++) {
            String s=role.get(i);
            if(s.endsWith("女巫")||s.endsWith("预言家")
                    ||s.endsWith("猎人")||s.endsWith("白痴")) {
                priest++;
            }else if(s.endsWith("平民")) {
                civilians++;
            }else if(s.endsWith("狼人")) {
                werewolf++;
            }
        }

        //无神职人员或平民存活，狼人获胜
        if(priest==0||civilians==0) {
            System.out.println("游戏结束，恭喜狼人获得胜利！");
            return true;
        }else if(werewolf==0) {//无狼人存活，好人获胜
            System.out.println("游戏结束，恭喜狼人获得胜利！");
            return true;
        }else {//游戏未结束
            return false;
        }

    }

    /*
     * 每次从已知数组随机一个数，然后将数组的最后一个值 赋值给前面随机到的数的位置上，
     * 然后将长度-1，再从原数组下标-1的数组中随机。 运算次数就是数组长度.
     */
    //角色分配
    private void allocation() {
        // TODO Auto-generated method stub

        int count = identity.length;
        int cbRandCount = 0;// 索引
        int cbPosition = 0;// 位置
        int i=0;
        do {
            Random rand = new Random();
            int r = count - cbRandCount;
            cbPosition = rand.nextInt(r);
            role.add(i++, i+identity[cbPosition]);
            cbRandCount++;
            identity[cbPosition] = identity[r - 1];// 将最后一位数值赋值给已经被使用的cbPosition
        }while(cbRandCount <count);

        System.out.println("玩家所分配到的角色如下：");
        for(String s:role) {
            System.out.println("玩家"+s);
        }
    }

}
