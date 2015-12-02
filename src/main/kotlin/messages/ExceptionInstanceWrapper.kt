package messages

import java.math.BigInteger

/**
 * Created by palfi on 2015-11-21.
 */
data class ExceptionInstanceWrapper(var fromWho: BigInteger = BigInteger("0"),
                                    var toWho: BigInteger = BigInteger("0"),
                                    var timeInMillis: Long = 0,
                                    var longitude: Double = 0.0,
                                    var latitude: Double = 0.0,
                                    var exceptionTypeId: Int = 0,
                                    var instanceId: BigInteger = BigInteger("0"),
                                    var pointsForSender: Int = 25,
                                    var pointsForReceiver: Int = -20,
                                    var city: String = "unknown",
                                    var question: Question = Question()) {}