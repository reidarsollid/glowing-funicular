package no.sollid.kotlin.actor

import co.paralleluniverse.fibers.Suspendable
import co.paralleluniverse.kotlin.Actor
import co.paralleluniverse.kotlin.register
import co.paralleluniverse.kotlin.spawn
import java.util.concurrent.TimeUnit

data class Rectangle(val height: Int, val width: Int)
data class Circle(val radius: Long)
object End

class AreaActor() : Actor() {
    @Suspendable override fun doRun() {
        //TODO: Switch to tailrec fun
        while(true) {
            receive(3, TimeUnit.SECONDS) {
                when (it) {
                    is Rectangle ->
                        println ("Area of Rectangle is ${(it.height + it.width)}")
                    is Circle ->
                        println("Area of Cicrle is ${Math.PI * it.radius * it.radius}")
                    End ->
                        return
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    val pid = spawn(register("AreaActor", AreaActor()))
    pid.send(Rectangle(3, 4))
    pid.send(Circle(2))
    pid.send(End)
}
