
sealed class Message {
    class Hello(val txt: String) : Message()
    object Goodbye : Message()
}

class PatternMatching(val txt: String) {
    fun match(msg: Message) {
        when(msg) {
            is Message.Hello  ->
                println(msg.txt)
            is Message.Goodbye ->
                    println(Message.Goodbye)
        }
    }
}

fun main(args: Array<String>) {
    val matcher = PatternMatching("")
    matcher.match(Message.Hello("Reidar"))
    matcher.match(Message.Goodbye)
}
