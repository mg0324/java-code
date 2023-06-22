package org.mango.demo.mt.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 递归任务，带返回值的
 * @Author: mango
 * @Date: 2022/6/27 10:18 下午
 */
public class SumTask extends RecursiveTask<Long> implements Callable<Long> {
    // 临界值
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public SumTask(long start,long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0L;
        boolean canSum = (end-start) < THRESHOLD;
        if(canSum){
            for(long i=start;i<=end;i++){
                sum += i;
            }
        }else{
            // 分成100个小任务
            int count = 100;
            long step = (end-start)/count;
            List<SumTask> sumTasks = new ArrayList<>();
            // 游标
            long cursor = start;
            for(int i=0;i<count;i++){
                long theEnd = cursor + step;
                if(theEnd > end){
                    theEnd = end;
                }
                SumTask sumTask = new SumTask(cursor,theEnd);
                // 游标下移动
                cursor += step + 1;
                // 将任务加入集合，并开启子任务
                sumTasks.add(sumTask);
                sumTask.fork();
            }
            // 合并子任务结果
            for (SumTask sumTask : sumTasks){
                sum += sumTask.join();
            }
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask sumTask = new SumTask(1,123456789L);
        Long result = forkJoinPool.invoke(sumTask);
        System.out.println("结果为:"+result);
//        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(sumTask);
//        try {
//            Long result2 = forkJoinTask.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        ExecutorService executorService = Executors.newWorkStealingPool();
        Future<Long> t = executorService.submit(sumTask);
        Long result2 = t.get();
        System.out.println(result2);

    }

    @Override
    public Long call() throws Exception {
        return compute();
    }
}
