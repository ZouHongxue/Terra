package proxy.annotation.service;

/**
 * @Author: zouhongxue
 * @Date: 2019/5/6 7:20 PM
 */
public interface TransactionProxy {
    /**
     * 前面逻辑
     * @return 是否成功
     */
    boolean before();

    /**
     * 后面逻辑
     */
    void after();
}
