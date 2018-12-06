package znode;

import com.lslnx.znode.ZkClinetCRUD;
import org.junit.Test;

import java.util.List;

/**
 * 节点操作test
 */
public class TestZnode {

    @Test
    public void test01(){
        System.out.println("test...");
        ZkClinetCRUD zkClinetCRUD = new ZkClinetCRUD();
//        zkClinetCRUD.del("/lslnx");
        zkClinetCRUD.createPersistent("/lslnx/0307","anc");
        System.out.println(zkClinetCRUD.readData("/lslnx"));
        List<String> list = zkClinetCRUD.getChild("/lslnx");
        list.forEach(e->{
            System.out.println(e);
        });

    }


}
