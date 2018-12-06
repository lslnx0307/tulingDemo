package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * test bean
 */

public class TestDemo {

    @Test
    public void arrayListAndLinkedList(){
        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        long arrayListStart = System.currentTimeMillis();
        for (int i = 0;i < 10000000;i++){
            arrayList.add(i);
        }
        long arrayListEnd = System.currentTimeMillis();
        System.out.println("arraylist 添加时间："+ (arrayListEnd - arrayListStart));
        long linkedListStart = System.currentTimeMillis();
        for (int i = 0;i < 10000000;i++){
            linkedList.add(i);
        }
        long linkedListEnd = System.currentTimeMillis();
        System.out.println("linkedlist 添加时间："+ (linkedListEnd - linkedListStart));
    }

    /**
     * linkedList 遍历
     */
    @Test
    public void linkedListItertor(){
        System.out.println(2 >> 1);
        System.out.println(2 << 2);
        HashMap hashMap = new HashMap();
        hashMap.put(null,null);

    }

    /**
     * 冒泡排序
     */
    @Test
    public void MaoPao(){
        int[] arrys = {9,1,4,2,5,3};
        for (int i=0;i<arrys.length;i++){
            for (int j = 0;j<arrys.length-1-i;j++){
                if(arrys[j]>arrys[j+1]){
                    int temp = arrys[j];
                    arrys[j] = arrys[j+1];
                    arrys[j+1] = temp;
                }
            }
        }

        for (int a :arrys){
            System.out.println(a);
        }
    }
}
