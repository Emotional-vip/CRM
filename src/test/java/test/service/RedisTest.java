package test.service;

import redis.clients.jedis.Jedis;

/**
 * 测试redis缓存
 *
 */
public class RedisTest {


    /**
     * 本地的缓存是没有密码的，是可以达到目的的。
     * 检查redis是否存活
     * @param url  服务器地址 　　　　
     * @param port 端口　　　　
     * @return
     */
    public static Integer getRedisIsOk(String url, int port) {
        int result = 0;
        try {
            //连接本地Redis服务
            Jedis jedis = new Jedis(url, port);
            String ping = jedis.ping();
            if (ping.equalsIgnoreCase("PONG")) {
                System.out.println("redis缓存有效！" + ping);
                result = 0;
            }
        } catch (Exception e) {
            System.out.println("redis缓存失败！");
            result = 1;
        }
        return result;
    }

    /**
     * 如果缓存有密码则，直接使用下面的方法就可以：
     * 检查redis是否存活
     * @param url 服务器地址
     * @param port 端口
     * @param password redis的密码
     * @return
     */
    public static Integer getRedisIsOk(String url, int port, String password) {
        int result = 0;
        try {
            //连接本地Redis服务
            Jedis jedis = new Jedis(url, port);
            jedis.auth(password);//密码
            String ping = jedis.ping();
            if (ping.equalsIgnoreCase("PONG")) {
                System.out.println("redis缓存有效！" + ping);
                result = 0;
            }
            jedis.close(); // 释放连接资源
        } catch (Exception e) {
            System.out.println("redis缓存失败！");
            result = 1;
        }
        return result;
    }


    public static void main(String[] args) {
        int res = 0;
//        res = getRedisIsOk("xxx.xxx.xxx.xxx", 6379);
        res = getRedisIsOk("xxx.xxx.xxx.xxx", 6379, "xxxxxxxxxxxx");
        if (res == 0) {
            System.out.println("redis缓存有效！" + res);
        } else {
            System.out.println("redis缓存失败！" + res);
        }
    }
}
