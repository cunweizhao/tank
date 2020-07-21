package com.zcw.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : TankFrame
 * @Description :
 * @Author : Zhaocunwei
 * @Date: 2020-07-20 19:46
 */
public class TankFrame extends Frame {


    Tank myTank = new Tank(200,400,Dir.DOWN,this,Group.GOOD);
    public static final int GAME_WIDTH=1080,GAME_HEIGHT=960;

    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();


    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        //添加监听
        this.addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
   //解决闪烁问题
    Image offScreenImage =null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage ==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    /**
     * 当窗口打开时（重新绘制时），会自动调用这个方法
     * 系统自带的画笔：Graphics
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量："+bullets.size(),10,60);
        g.drawString("敌人的数量"+tanks.size(),10,80);
        g.drawString("爆炸的数量"+explodes.size(),10,100);
        g.setColor(c);
        myTank.paint(g);
        for(int i=0;i<bullets.size();i++){
           bullets.get(i).paint(g);
        }
       //画敌方坦克
        for(int i=0; i<tanks.size();i++){
            tanks.get(i).paint(g);
        }

        //判断子弹是否与坦克相撞
        for(int i =0;i<bullets.size();i++){
            for(int j=0;j<tanks.size();j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        //画出爆炸
        for(int i=0;i<explodes.size();i++){
            explodes.get(i).paint(g);
        }
    }
    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bU = false;
        boolean bR =false;
        boolean bD = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                   bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                   bD=true;
                    break;
                default:
                    break;
            }
           setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;
                case KeyEvent.VK_ENTER :
                   myTank.fire();
                   break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir(){
            if(!bL && !bU && !bR && !bD){
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if(bL){
                    myTank.setDir(Dir.LEFT);
                }
                if(bU) {
                    myTank.setDir(Dir.UP);
                }
                if(bR){
                    myTank.setDir(Dir.RIGHT);
                }
                if(bD){
                    myTank.setDir(Dir.DOWN);
                }
            }


        }

    }
}
