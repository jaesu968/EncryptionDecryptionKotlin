package encryptdecrypt

import jdk.internal.joptsimple.internal.Messages.message

fun main() {


    // encryption
    // a to b, b to c, ... z to a
    // A to B, B to C, ... Z to A
    // if there is a number or a space, it should be printed as is
    val message = readln() // first line of input is the string to be encrypted
    val key = readln().toInt() // second line of input is the key, which is an integer
    for (i in message.indices) {
        // shift the character by the key
        when (val c = message[i]) {
            in 'a'..'z' -> {
                val shiftedChar = (((c - 'a') + key) % 26 + 'a'.code).toChar()
                print(shiftedChar)
            }
            in 'A'..'Z' -> {
                val shiftedChar = (((c - 'A') + key) % 26 + 'A'.code).toChar()
                print(shiftedChar)
            }
            else -> {
                print(c)
            }
        }
    }
}