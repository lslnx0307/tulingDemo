package com.lslnx.test.znode;

import com.lslnx.watcher.ZookeeperWatcher;
import com.lslnx.znone.ZookeeperCrud;
import org.apache.zookeeper.KeeperException;
import org.junit.Test;

/**
 * 节点操作test
 */
public class TestZnode {

    @Test
    public void test01(){
        ZookeeperCrud zookeeperCrud = new ZookeeperCrud();
//        zookeeperCrud.createPersistent("/lslnx","123");
//        System.out.println(zookeeperCrud.getData("/lslnx"));
        zookeeperCrud.createEphemeral("/aaaa","123");
    }

    @Test
    public void watcherTest(){
        try {
            ZookeeperWatcher zookeeperWatcher = new ZookeeperWatcher();
            if(zookeeperWatcher.exists("/lslnx",true) != null){
                zookeeperWatcher.deleteRecursive("/lslnx");
            }
            zookeeperWatcher.createPersistent("/lslnx","11");
            System.out.println(zookeeperWatcher.getData("/lslnx",true));
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
