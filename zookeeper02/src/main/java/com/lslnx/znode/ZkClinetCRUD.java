package com.lslnx.znode;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.List;

/**
 * zkClient api 操作
 */
public class ZkClinetCRUD<T> {

    private ZkClient zkClient;

    private String connectString = "192.168.1.8:2181";

    /**
     * 无参构造，创建zkclient 实例化
     */
    public ZkClinetCRUD(){
        this.zkClient = new ZkClient(connectString,5000,5000,new SerializableSerializer());
    }

    /**
     * 创建持久节点
     * @param path
     */
    public void createPersistent(String path,Object data){
        zkClient.createPersistent(path,data);
    }

    public void createPersistent(String path){
        zkClient.createPersistent(path);
    }

    /**
     * 读取数据
     * @param path
     * @return
     */
    public T readData(String path){
        return zkClient.readData(path);
    }

    public List<String> getChild(String path){
        return zkClient.getChildren(path);
    }

    /**
     * 删除节点
     * @param path
     */
    public void del(String path){
        zkClient.deleteRecursive(path);
    }

}
