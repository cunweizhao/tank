package com.zcw.tank;

import java.awt.*;

/**
 * @ClassName : Bullet
 * @Description : 炮弹
 * @Author : Zhaocunwei
 * @Date: 2020-07-20 20:21
 */
public class Bullet {
    private static final int SPEED =7;
    public static final int WIDTH=ResourceMgr.bulletD.getWidth();
    public static  final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x,y;
    private Dir dir;
    private TankFrame tf =null;
    private boolean liveing = true;
    private Group group = Group.BAD;

    Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir,TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        tf.bullets.add(this);

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
    }
    public void paint(Graphics g){
        if(!liveing){
            tf.bullets.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
        move();
    }
    private void move(){
        switch (dir){
            case LEFT:
                x -=SPEED;
                break;
            case UP:
                y -=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
        //需要更新 rect 对象
        rect.x = this.x;
        rect.y = this.y;

        if(x<0 || y <0 || x > TankFrame.GAME_WIDTH || y> TankFrame.GAME_HEIGHT){
            liveing =false;
        }
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()){
            return;
        }
        //判断两个方框是否相交
        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 -Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX,eY,tf));
        }
    }

    private void die() {
        this.liveing=false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
