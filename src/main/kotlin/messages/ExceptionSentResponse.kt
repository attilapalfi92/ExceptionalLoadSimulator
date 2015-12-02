package messages

/**
 * Created by palfi on 2015-12-02.
 */
public data class ExceptionSentResponse(var exceptionShortName: String,
                                        var sendersPoints: Int,
                                        var receiversPoints: Int,
                                        var instanceWrapper: ExceptionInstanceWrapper);