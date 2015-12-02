package executor

import factory.getExceptionBetweenRandomUsers
import rest.constructThrowingEndpoint
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created by palfi on 2015-12-02.
 */
class ThrowingExecutor(rate: Int, durationSeconds: Int, notificationQueue: LinkedBlockingQueue<String>) :
        AbstractExecutor(rate, durationSeconds, notificationQueue) {

    private val throwingEndpoint = constructThrowingEndpoint()

    override protected fun doOneJob() {
        val response = throwingEndpoint.throwException(getExceptionBetweenRandomUsers()).execute()
        val notifPrefix = "${counter++}. throwing."
        notify(notifPrefix, response.isSuccess, response.code(), response.message())
    }
}