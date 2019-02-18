package cn.wbnull.springbootdemo.util;

import java.util.List;

/**
 * List 工具类
 *
 * @author dukunbiao(null) 2018-09-15
 *         https://github.com/dkbnull/Util
 */
public class ListUtils {

    private ListUtils() {
    }

    /**
     * 检查List是否为空
     *
     * @param list 待检查List
     * @return true/false
     */
    public static boolean isEmpty(List list) {
        return (list == null || list.isEmpty());
    }
}
