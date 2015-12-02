package executor

import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created by palfi on 2015-12-02.
 */
abstract class AbstractExecutor(protected val rate: Int,
                                protected val durationSeconds: Int,
                                protected val notificationQueue: LinkedBlockingQueue<String>) : Runnable {

    @Volatile
    public var finished = false

    protected val executor = Executors.newFixedThreadPool(rate)
    protected val timeBetweenThrows: Int = 1000 / rate
    protected var counter: Int = 1
    protected var successCounter: Int = 1
    protected var failCounter: Int = 1

    override public fun run() {
        val startTime = System.currentTimeMillis()
        while ( timeIsNotOver(startTime) ) {
            executor.execute({
                doOneJob()
            })
            Thread.sleep(timeBetweenThrows.toLong())
        }
        finished = true
    }

    private fun timeIsNotOver(startTime: Long) = System.currentTimeMillis() - startTime < durationSeconds * 1000

    protected abstract fun doOneJob();

    protected fun notify(notifPrefix: String, isSuccess: Boolean, code: Int, message: String) {
        if (isSuccess) {
            notificationQueue.offer("$notifPrefix ${successCounter++}. successful.")
        } else {
            notificationQueue.offer("$notifPrefix ${failCounter++}. failed. " +
                    "Reason: $code. $message")
        }
    }
}