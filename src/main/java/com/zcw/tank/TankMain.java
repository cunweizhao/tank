package com.zcw.tank;

/**
 * @ClassName : TankMain
 * @Description :
 * @Author : Zhaocunwei
 * @Date: 2020-07-20 12:53
 */
public class TankMain {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        //初始化地方坦克
        for(int i =0;i<initTankCount;i++){
            tf.tanks.add(new Tank(50 + i*80 ,200,Dir.DOWN,tf,Group.BAD));
        }
        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
