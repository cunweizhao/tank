package com.zcw.tank;

import java.awt.*;

/**
 * @ClassName : Bullet
 * @Description : 炮弹
 * @Author : Zhaocunwei
 * @Date: 2020-07-20 20:21
 */
public class Explode {
    public static final int WIDTH=ResourceMgr.explodes[0].getWidth();
    public static  final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x,y;
    private TankFrame tf =null;

    private int step =0;

    public Explode(int x, int y,  TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }
    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step >=ResourceMgr.explodes.length){
            tf.explodes.remove(this);
        }
    }

}
