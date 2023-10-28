package com.yupi.springbootinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolExecutorConfig {

    ThreadFactory threadFactory = new ThreadFactory() {
        private int count = 1;
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("my-thread-" + count++);
            return thread;
        }
    };
    @Bean
    public ThreadPoolExecutor getThreadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4), threadFactory, new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
