package executor

import factory.getAppStartRequest
import factory.getRandomId
import rest.constructAppStartEndpoint
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created by palfi on 2015-12-02.
 */
class AppStartingExecutor(rate: Int, durationSeconds: Int, notificationQueue: LinkedBlockingQueue<String>) :
        AbstractExecutor(rate, durationSeconds, notificationQueue) {

    private val appStartEndpoint = constructAppStartEndpoint()

    override protected fun doOneJob() {
        val request = getAppStartRequest(getRandomId())
        val response = appStartEndpoint.firstAppStart(request).execute()
        val notifPrefix = "${counter++}. app start."
        notify(notifPrefix, response.isSuccess, response.code(), response.message())
    }
}