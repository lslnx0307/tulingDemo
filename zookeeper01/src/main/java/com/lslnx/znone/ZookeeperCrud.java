package com.lslnx.znone;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * 原生的zk client操作znode节点
 */
public class ZookeeperCrud {
    //定义链接地址集群用逗号分隔
    private String connectString = "192.168.1.8:2181";

    private ZooKeeper zooKeeper;

    public ZookeeperCrud() {
        try {
            zooKeeper = new ZooKeeper(connectString, 50000, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建永久节点
     *
     * @param path
     * @param data
     * @return
     */
    public String createPersistent(String path, String data) {
        try {
            return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建临时节点(临时节点是基于当前session，当session失效，或者session中断了，这个节点就会被删除)
     *
     * @param path
     * @param data
     * @return
     */
    public String createEphemeral(String path, String data) {
        try {
            return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数据
     */
    public String getData(String path) {
        try {
            byte[] data = zooKeeper.getData(path, false, null);
            return data == null ? "null":new String(data);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
