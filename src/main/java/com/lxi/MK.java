package com.lxi;

import java.util.Random;

class MK extends Person {

        public MK() {

                val = 700;

                coldTime = 2.5;

                fight = 50;

                chanceDefense = 6;

                chanceHit = 3;

                waitTime = 0;

        }

        boolean hitFlag = false;

        boolean defenseFlag = false;

        Random rand = new Random();

        public void hit(Person p) {

                if (rand.nextInt(10) < chanceHit) {

                        p.waitTime = 3; //使对方晕眩3s

                        hitFlag = true;

                }

                int hurt = p.defense(this);

                p.val = p.val - hurt;

                if (p.val <= 0) {

                        System.out.println(this.getClass().getSimpleName() + "胜出!");

                        System.exit(0);

                }

                System.out.println(this.getClass().getSimpleName() + "攻击"

                        + p.getClass().getSimpleName() + ","

                        + this.getClass().getSimpleName()

                        + (this.hitFlag ? "发动攻击技能 " : "未发动攻击技能 ")

                        + p.getClass().getSimpleName()

                        + (this.defenseFlag ? "发动防御技能 " : "未发动防御技能 ")

                        + this.getClass().getSimpleName() + ":" + this.val + ","

                        + p.getClass().getSimpleName() + ":" + p.val);

                hitFlag = false;

                defenseFlag = false;

        }

        public int defense(Person p) {

                if (rand.nextInt(10) < chanceDefense) {

                        defenseFlag = true;

                        return p.fight / 2; //防御技能发动，伤害减半

                }

                return p.fight;

        }

}


