package com.luck.luckcloud.baselibrary.utils;

import java.util.Random;

/**
 * 随机数工具类
 * Created by fa on 2018/7/19.
 */

public class RandomUtils {

    /**
     * 生成从 from（包含） 到 to（不包含） 之间的随机数
     *
     * @param from 起始（包含）
     * @param to   结束（不包含）
     */
    public static int randomInt(int from, int to) {
        Random random = new Random();
        return random.nextInt(to - from) + from;
    }
}
