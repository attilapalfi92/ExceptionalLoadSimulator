import executor.AbstractExecutor
import executor.AppStartingExecutor
import executor.ThrowingExecutor
import java.util.*
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

/**
 * Created by palfi on 2015-11-21.
 */
fun main(args: Array<String>) {
    val application = Application()
    application.startApp()
}

class Application {
    private val executorCount = 2
    private var throwRate: Int = 0
    private var startRate: Int = 0
    private var durationSeconds: Int = 0
    private val notificationQueue = LinkedBlockingQueue<String>()
    private val executors = arrayOfNulls<AbstractExecutor>(executorCount)

    fun startApp() {
        readParameters()
        initExecutors()
        doStart()
        println("Load test is over.")
        System.exit(0)
    }

    private fun readParameters() {
        val scanner = Scanner(System.`in`)
        println("How long should the simulation last? (s)")
        durationSeconds = scanner.nextInt()
        println("How many exceptions should be thrown each second? (exceptions / s)")
        throwRate = scanner.nextInt()
        println("How many app starts should be requested each second? (app starts / s)")
        startRate = scanner.nextInt()
    }

    private fun initExecutors() {
        executors[0] = ThrowingExecutor(throwRate, durationSeconds, notificationQueue)
        executors[1] = AppStartingExecutor(startRate, durationSeconds, notificationQueue)
    }

    private fun doStart() {
        val threads = Array(executors.size, { i -> Thread(executors[i]) })
        threads.forEach { it.start() }
        processNotifications()
        threads.forEach { it.join() }
    }

    private fun processNotifications() {
        while (anyExecutorIsRunning()) {
            val notification: String? = notificationQueue.poll(1, TimeUnit.SECONDS)
            notification?.let { println(notification) }
        }
    }

    private fun anyExecutorIsRunning(): Boolean {
        executors.forEach {
            it?.let {
                if (!it.finished) {
                    return true;
                }
            }
        }
        return false;
    }
}

