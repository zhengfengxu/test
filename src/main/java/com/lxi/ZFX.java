package com.lxi;


import java.util.Random;

class ZFX extends Person {

        public ZFX() {

                val = 999999999;

                coldTime = 0.1;

                fight = 999999;

                chanceHit = 10;

                chanceDefense =10;

                waitTime = 0;

        }

        int count = 0; //防御技能发动的次数

        int temp = 999999; //攻击力，值同fight

        boolean hitFlag = false;

        boolean defenseFlag = false;

        Random rand = new Random();

        public void hit(Person p) {

                if (rand.nextInt(10) < chanceHit) {

                        fight = fight * 2; //发动双倍攻击

                        hitFlag = true;

                }

                int hurt = p.defense(this);

                p.val = p.val - hurt;

                fight = temp; //还原为单倍攻击

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

                        if (count != 0) {

                                p.val = p.val - p.fight;

                                count++;

                                defenseFlag = true;

                                if (p.val <= 0) {

                                        System.out.println(this.getClass().getSimpleName() + "胜出!");

                                        System.exit(0);

                                }

                        }

                }

                return p.fight;

        }

}


