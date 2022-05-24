package com.lxi;


abstract class Person {

        int val; //生命值

        double coldTime; //冷却时间

        int waitTime; //晕眩时间

        int fight; //攻击力

        int chanceHit; //发起主动技能的概率

        int chanceDefense; //发起防御技能的概率

        abstract void hit(Person p); //攻击技能

        abstract int defense(Person p); //防御技能，返回被伤害点数

}


