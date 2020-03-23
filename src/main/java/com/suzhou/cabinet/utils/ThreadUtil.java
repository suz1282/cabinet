package com.suzhou.cabinet.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUtil {
    public static ExecutorService executorService = new ThreadPoolExecutor(
            10,
            40,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            //线程工厂
            (Runnable r) -> new Thread(r),
            //在发起线程中运行
            new ThreadPoolExecutor.CallerRunsPolicy());
}
