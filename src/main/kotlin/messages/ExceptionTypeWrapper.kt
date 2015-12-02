package messages

/**
 * Created by palfi on 2015-12-02.
 */
data class ExceptionTypeWrapper(
        var id: Int = 0,
        var version: Int = 0,
        var shortName: String = "",
        var prefix: String = "",
        var description: String = "",
        var type: String = "",
        var submitter: Submitter = Submitter(),
        var voteCount: Int = 0
)

data class Submitter(var firstName: String = "",
                     var lastName: String = "")