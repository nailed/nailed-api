package jk_5.nailed.api.scheduler;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;

/**
 * No description given
 *
 * @author jk-5
 */
public interface Scheduler extends ScheduledExecutorService {

    @Nonnull Future<?> submit(@Nonnull Runnable task);
    @Nonnull <T> Future<T> submit(@Nonnull Runnable task, T result);
    @Nonnull <T> Future<T> submit(@Nonnull Callable<T> task);
    @Nonnull ScheduledFuture<?> schedule(@Nonnull Runnable command, long delay, @Nonnull TimeUnit unit);
    @Nonnull <V> ScheduledFuture<V> schedule(@Nonnull Callable<V> callable, long delay, @Nonnull TimeUnit unit);
    @Nonnull ScheduledFuture<?> scheduleAtFixedRate(@Nonnull Runnable command, long initialDelay, long period, @Nonnull TimeUnit unit);
    @Nonnull ScheduledFuture<?> scheduleWithFixedDelay(@Nonnull Runnable command, long initialDelay, long delay, @Nonnull TimeUnit unit);
    @Nonnull <T> List<Future<T>> invokeAll(@Nonnull Collection<? extends Callable<T>> tasks) throws InterruptedException;
    @Nonnull <T> List<Future<T>> invokeAll(@Nonnull Collection<? extends Callable<T>> tasks, long timeout, @Nonnull TimeUnit unit) throws InterruptedException;
    @Nonnull <T> T invokeAny(@Nonnull Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;
    <T> T invokeAny(@Nonnull Collection<? extends Callable<T>> tasks, long timeout, @Nonnull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
    void execute(@Nonnull Runnable command);

    @Nonnull Future<?> submitSync(@Nonnull Runnable task);
    @Nonnull <T> Future<T> submitSync(@Nonnull Runnable task, T result);
    @Nonnull <T> Future<T> submitSync(@Nonnull Callable<T> task);
    @Nonnull ScheduledFuture<?> scheduleSync(@Nonnull Runnable command, long delay, @Nonnull TimeUnit unit);
    @Nonnull <V> ScheduledFuture<V> scheduleSync(@Nonnull Callable<V> callable, long delay, @Nonnull TimeUnit unit);
    @Nonnull ScheduledFuture<?> scheduleAtFixedRateSync(@Nonnull Runnable command, long initialDelay, long period, @Nonnull TimeUnit unit);
    @Nonnull ScheduledFuture<?> scheduleWithFixedDelaySync(@Nonnull Runnable command, long initialDelay, long delay, @Nonnull TimeUnit unit);
    @Nonnull <T> List<Future<T>> invokeAllSync(@Nonnull Collection<? extends Callable<T>> tasks) throws InterruptedException;
    @Nonnull <T> List<Future<T>> invokeAllSync(@Nonnull Collection<? extends Callable<T>> tasks, long timeout, @Nonnull TimeUnit unit) throws InterruptedException;
    @Nonnull <T> T invokeAnySync(@Nonnull Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException;
    <T> T invokeAnySync(@Nonnull Collection<? extends Callable<T>> tasks, long timeout, @Nonnull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
    void executeSync(@Nonnull Runnable command);
}
