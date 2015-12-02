package messages

/**
 * Created by palfi on 2015-11-21.
 */
data class Question(val text: String? = null,
                    val yesIsCorrect: Boolean = true,
                    val hasQuestion: Boolean = false,
                    var answered: Boolean = true,
                    var answeredCorrectly: Boolean = true) {}