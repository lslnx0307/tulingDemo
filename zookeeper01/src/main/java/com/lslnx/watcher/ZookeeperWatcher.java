package com.lslnx.watcher;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZookeeperWatcher implements Watcher {
    private String connectString = "192.168.1.8:2181";

    private ZooKeeper zooKeeper;

    public ZookeeperWatcher() {
        try {
            zooKeeper = new ZooKeeper(connectString, 50000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {

        // 连接状态
        Event.KeeperState keeperState = event.getState();
        // 事件类型
        Event.EventType eventType = event.getType();
        // 受影响的path
        String path = event.getPath();
        System.out.println("连接状态:"+keeperState+",事件类型："+eventType+",受影响的path:"+path);
//        //step 1:
//        //  System.out.println("连接状态:"+keeperState+",事件类型："+eventType+",受影响的path:"+path);
//
//        //step:2
//        try {
//            if(null!=this.exists("/lslnx",true)) {
//                System.out.println("内容:"+ this.getData("/lslnx", true));
//            }
//            System.out.println("连接状态:"+keeperState+",事件类型："+eventType+",受影响的path:"+path);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("--------------------");
    }


    public Object exists(String path, boolean  watcher) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path,watcher);
    }

    /**
     * 创建持久节点
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

    public String getData(String path,boolean watcher){
        try {
            byte[] data = zooKeeper.getData(path, watcher, null);
            data = (data == null)?"null".getBytes():data;
            return new String(data);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    /***
     * 删除
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void deleteRecursive(String path) throws KeeperException, InterruptedException {
        ZKUtil.deleteRecursive(zooKeeper, path);
    }


}
