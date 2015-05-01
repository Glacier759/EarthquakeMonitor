package com.glacier.earthquake.monitor.server.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Date;

/**
 * Created by glacier on 15-5-1.
 */
public class BloomFilter implements Serializable {  //为了将对象序列化为对象流，须实现Seriaizable接口
    private BitSet bitSet;
    private int bitSetSize, elementNumber;
    private int[] hashSeed = {7,11,13,19,31,37};
    private int addCount = 0;
    private String recodeDate;

    /**
     * 布隆过滤器构造函数
     * @param elementNumber 布隆过滤器将要处理的url数量
     */
    public BloomFilter(int elementNumber) {
        this.elementNumber = elementNumber;
        this.bitSetSize = elementNumber * 100;
        bitSet = new BitSet();

        SimpleDateFormat format = new SimpleDateFormat("dd");
        setRecodeDate(format.format(new Date()));
    }

    /**
     * 添加一个元素进布隆过滤器中
     * @param value 加入布隆过滤器的url(已获取过的URL)
     * @return true表示加入成功，false表示加入失败
     */
    public boolean add(String value) {
        try {
            for (int i = 0; i < hashSeed.length; i++) {
                long key = hashKey(value, i);

                long key1 = (key >> 32) & 0xfffffff;
                long key2 = key & 0xfffffff;

                setBit(key1 % bitSetSize);
                setBit(key2 % bitSetSize);
            }
            addCount ++;
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断元素是否在布隆过滤器中
     * @param value 判断url是否在布隆过滤器中(是否已经获取过)
     * @return true表示存在，false表示不存在
     */
    public boolean contains(String value) {
        for ( int i = 0; i < hashSeed.length; i ++ ) {
            long key = hashKey(value, i);

            long key1 = (key >> 32) & 0xfffffff;
            long key2 = key & 0xfffffff;

            if ( getBit(key1 % bitSetSize) == false )    return false;
            if ( getBit(key2 % bitSetSize) == false )    return false;
        }
        return true;
    }

    /**
     * 获取已经加入布隆过滤器中的元素个数
     * @return 返回已经加入布隆过滤器的url个数
     * */
    public int getAddCount() {  return addCount;    }

    /**
     * 计算key值哈希函数
     * @param value 需要计算的url
     * @param index 哈希种子数组中的下标索引，表示进行到第几次哈希计算
     * @return 返回哈希值
     * */
    private long hashKey(String value, int index) {
        long key = 0;
        for (int i = 0; i < value.length(); i ++) {
            key = key * hashSeed[index] + value.charAt(i);
        }
        return key;
    }

    /**
     * 设置bitSet中对应位置为true
     * @param key 设置bitSet中相应下标位为1
     * */
    private void setBit(long key) {
        bitSet.set(new Integer(String.valueOf(key)), true);
    }

    /**
     * 获取bitSet中相应下标位的值
     * @param key 指定下标位
     * @return bitSet中对应的值
     * */
    private boolean getBit(long key) {
        return bitSet.get(new Integer(String.valueOf(key)));
    }

    /**
     * 设置使用布隆过滤器的当天日期
     * @param recodeDate 日期对应的字符串
     * */
    public void setRecodeDate(String recodeDate) {
        this.recodeDate = recodeDate;
    }

    /**
     * 返回最后一次使用布隆过滤器的当天日期
     * @return 当天日期对应的字符串
     * */
    public String getRecodeDate() { return this.recodeDate; }
}