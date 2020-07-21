package com.zcw.tank;


import java.awt.*;
import java.util.Random;

/**
 * @ClassName : tank
 * @Description :
 * @Author : Zhaocunwei
 * @Date: 2020-07-20 12:03
 */
public class Tank {
     int x, y;
     Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean moving = true;
     TankFrame tf = null;
    private boolean living = true;
    //创建分组，
     Group group = Group.BAD;
    private Random random = new Random();

    Rectangle rect = new Rectangle();
    //FourDirFireStrategy 大招
    FireStrategy fs = new DefaultFireStrategy();

    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;


        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.tanks.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(
                        this.group == Group.GOOD ? ResourceMgr.goodTankL
                                : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(
                        this.group == Group.GOOD ? ResourceMgr.goodTankU
                                : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(
                        this.group == Group.GOOD ? ResourceMgr.goodTankR
                                : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(
                        this.group == Group.GOOD ? ResourceMgr.goodTankD
                                : ResourceMgr.badTankD, x, y, null);
                break;
        }

        move();
    }

    private void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }


        //敌人坦克打出子弹
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.fire();
            //创建随机方向
            randmoDir();
        }
        //边界检测
        boundsCheck();

        //需要更新 rect 对象
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.x < 2){
            x=2;
        }
        if(this.y <28){
            y =28;
        }
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH -2){
            x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
        }
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2){
            y = TankFrame.GAME_HEIGHT -Tank.HEIGHT - 2;
        }
    }

    public void randmoDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }


    public void die() {
        this.living = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
