import co.paralleluniverse.actors.LocalActor
import co.paralleluniverse.fibers.Suspendable
import co.paralleluniverse.kotlin.Actor
import co.paralleluniverse.kotlin.register
import co.paralleluniverse.kotlin.spawn
import java.util.concurrent.TimeUnit

data class Msg(val txt: String)
//object End

class MsgActor() : Actor() {
    @Suspendable override fun doRun() {
        //TODO: Change while(trye) with tail recursion
        while (true) {
            receive(1, TimeUnit.SECONDS) {
                when (it) {
                    is Msg ->
                        println(it.txt)
                    End ->
                        return
                }

            }
        }
    }
}

tailrec fun MsgLoop(message: String) {
    MsgLoop(message)
}

fun main(args: Array<String>) {
    val pid = spawn(register("messageActor", MsgActor()))
    pid.send(Msg("Hello"))
    pid.send(End)
    LocalActor.join(pid)
}

