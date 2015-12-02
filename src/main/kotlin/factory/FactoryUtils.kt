package factory

import com.sun.javaws.exceptions.InvalidArgumentException
import messages.AppStartRequest
import messages.ExceptionInstanceWrapper
import messages.Question
import java.math.BigInteger
import java.util.*

/**
 * Created by palfi on 2015-12-02.
 */
private val MIN_ID: Int = 1
private val MAX_ID: Int = 1000
private val ID_PREFIX = "100000000"

public fun getAppStartRequest(testUserId: Int): AppStartRequest {
    validateId(testUserId)
    return AppStartRequest(
            BigInteger(getFullId(testUserId)),
            emptyList(),
            "TEST_DEVICE_$testUserId",
            "TEST_GCM_ID_$testUserId",
            getFriendsOf(testUserId).map { BigInteger(getFullId(it)) },
            0,
            "Test_$testUserId",
            "Test_$testUserId",
            "TEST_DEVICE_$testUserId"
    )
}

public fun getExceptionInstance(fromId: Int, toId: Int): ExceptionInstanceWrapper {
    return ExceptionInstanceWrapper(
            fromWho = BigInteger(getFullId(fromId)),
            toWho = BigInteger(getFullId(toId)),
            timeInMillis = System.currentTimeMillis(),
            longitude = (Math.random() * 360) - 180,
            latitude = (Math.random() * 180) - 90,
            exceptionTypeId = (Math.random() * 26).toInt(),
            question = Question(hasQuestion = false)
    )
}

public fun getExceptionBetweenRandomUsers(): ExceptionInstanceWrapper {
    return getExceptionInstance(getRandomId(), getRandomId())
}

public fun getFriendsOf(idEnding: Int): List<Int> {
    val min = if (idEnding - 10 < MIN_ID) MIN_ID else idEnding - 10;
    val max = if (idEnding + 10 > MAX_ID) MAX_ID else idEnding + 10;
    val list = ArrayList<Int>(20)
    for (i in min..max) {
        list.add(i)
    }
    return list
}

public fun getRandomId(): Int = (Math.random() * (MAX_ID - MIN_ID + 1)).toInt() + MIN_ID

private fun validateId(id: Int) {
    if (MIN_ID < 1 || id > MAX_ID) {
        throw InvalidArgumentException(arrayOf("testUserId must be [$MIN_ID, $MAX_ID]"))
    }
}

private fun getFullId(idEnding: Int): String = "$ID_PREFIX$idEnding"
