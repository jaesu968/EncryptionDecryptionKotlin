package encryptdecrypt

import sun.security.krb5.internal.crypto.Aes128.encrypt


fun main(args: Array<String>) {

    // intialize default values for mode, key, and data
    var mode = "enc"
    var key = 0
    var data = ""

    // variables will get arguments by when the program is started so no need to initialize from the user

    // loop through the args array to grab the mode , key , and data
    for (i in args.indices) {
        when (args[i]) {
            // use args.size to find the correct mode otherwise use enc as default
            "-mode" -> mode = if (i + 1 < args.size) args[i + 1] else "enc"
            // determine the key using args.size, this is how you will modify the encryption or decryption
            "-key" -> key = if (i + 1 < args.size) args[i + 1].toInt() else 0
            // check for data message being passed , if nothing passed it is an empty string
            "-data" -> data = if (i + 1 < args.size) args[i+ 1] else ""
        }
    }
    // execute based on mode
    when (mode) {
        "enc" -> {
            encryption(data, key)
            println() // print blank line
        }
        "dec" -> {
            decryption(data, key)
            println()
        }
    }
}

// Encryption function (Unicode shift)
fun encryption(message: String, key: Int){
    // encryption
    // shift every character by the key
    for (char in message){
        // Get the Unicode value,add the key, convert back to char
        val shiftedChar = (char.code + key).toChar()
        print(shiftedChar)
    }
}

// Decryption function (Unicode shift)
fun decryption(message: String, key: Int){
    // decryption
    // shift every character back by the key
    for (char in message){
        // Get the Unicode value, subtract the key, convert back to char
        val shiftedChar = (char.code - key).toChar()
        print(shiftedChar)
    }
}