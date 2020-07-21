package com.zcw.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName : PropertyMgr
 * @Description :获取配置文件信息
 * @Author : Zhaocunwei
 * @Date: 2020-07-11 20:27
 */
public class PropertyMgr {
    static Properties propes = new Properties();

    static {
        try {
            propes.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if(propes == null ){
            return null;
        }
        return  propes.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
