package messages

import java.math.BigInteger

/**
 * Created by palfi on 2015-11-21.
 */
data class AppStartRequest(
        var userFacebookId: BigInteger = BigInteger.ZERO,
        var knownExceptionIds: List<BigInteger> = listOf(),
        var deviceId: String = "",
        var gcmId: String = "",
        var friendsFacebookIds: List<BigInteger> = listOf(),
        var exceptionVersion: Int = 0,
        var firstName: String = "",
        var lastName: String = "",
        var deviceName: String = "") {}