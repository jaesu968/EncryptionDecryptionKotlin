package encryptdecrypt

import jdk.internal.joptsimple.internal.Messages.message

fun main() {

    // read the input from user to decide on encryption or d
    val operation = readln()
    // read the message to be encrypted or decrypted
    val message = readln()
    // read the key for encryption or decryption
    val key = readln().toInt()
    // if enc then encrypt, if dec then decrypt
    when (operation) {
        "enc" -> {
            encryption(message, key)
            println() // print a new line after encryption
        }
        "dec" -> {
            decryption(message, key)
            println() // print a new line after decryption
        }
        else -> {
            println("Invalid operation. Please enter 'enc' for encryption or 'dec' for decryption.")
        }
    }
}

// Encryption function
fun encryption(message: String, key: Int){
    // encryption
    // shift every character by the key
    for (char in message){
        // Get the Unicode value,add the key, convert back to char
        val shiftedChar = (char.code + key).toChar()
        print(shiftedChar)
    }
}

// Decryption function
fun decryption(message: String, key: Int){
    // decryption
    // shift every character back by the key
    for (char in message){
        // Get the Unicode value, subtract the key, convert back to char
        val shiftedChar = (char.code - key).toChar()
        print(shiftedChar)
    }
}