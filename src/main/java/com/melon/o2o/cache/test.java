package com.melon.o2o.cache;

import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * @ClassName test
 * @Description
 * @Author melon
 * @Date 2019-09-11 20:59
 * @Version
 */

public class test {

    public static void main(String[] args) {
        String ip = "127.0.0.1";
        int port = 6379;
        //连接本地Redis服务
        Jedis jedis = new Jedis(ip, port);
        jedis.auth("melon");
        //push
        String heartLogic = "ACT:heartbeat;CHKSUM:F2B95D05";
        System.out.println("rpush begin");
        for (int i = 0; i < 10000; i++) {
            jedis.rpush("HeartBeatLogic", heartLogic);
        }
        //查看服务器是否运行，打出pong表示OK
        System.out.println("rpush is OK============>" + jedis.ping() + ";bloop begin:" + new Date());

    }

//    public static void main(String[] args) {
//        int a = 0x122;
//        System.out.println(a/2);
//        System.out.println(2*1+2*16+1*16*16);
//    }
}
