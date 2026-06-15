package encryptdecrypt

import java.io.File


fun main(args: Array<String>) {

    // initialize default values for mode, key, and data, inPath and outPath
    var mode = "enc"
    var key = 0
    var data: String? = null // initialize as a nullable string since it might be empty or not present.
    var inPath: String? = null // initialize this to get the file path for input if -in is present
    var outPath: String? = null // initialize this to get the file path for output if -out is present
    var alg = "shift" // default algorithm is shift, but it can be changed to Unicode if -alg is specified.

    // parse arguments using a when statement to jump to the correct case for each argument.
    try {
        // iterate through the indices with a step of 2 (flag, then value) to ensure we are checking the flag and its corresponding value together.
        for (i in args.indices step 2){
            if (i + 1 >= args.size){
                println("Error: Missing value for argument ${args[i]}")
                return
            }
            when(args[i]){
                "-mode" -> mode = args[i + 1]
                "-key" -> key = args[i + 1].toInt()
                "-data" -> data = args[i + 1]
                "-in" -> inPath = args[i + 1]
                "-out" -> outPath = args[i + 1]
                "-alg" -> alg = args[i + 1]
            }
        }
    } catch (e: Exception){
        println(e.message + "Error: Invalid arguments.")
        return
    }

    // Determine Input Data (priority: -data > -in > default empty string)
    val finalData = when {
        data != null -> data
        inPath != null -> {
            try {
                File(inPath).readText()
            } catch (e: Exception){
                println(e.message +  "Error: The input file does not exist or cannot be read.")
                return
            }
        } else -> "" // If neither -data nor -in is provided, use an empty string as the default message.
    }

    // Process Data, use alg to determine which algorithm to use for encryption/decryption, and call the appropriate function with the final data, mode, and key.
    val result = when(alg){
        "unicode" -> unicodeAlgorithm(finalData, mode, key) // use Unicode algorithm to shift characters based on their Unicode values.
        "shift" -> shiftAlgorithm(finalData, mode, key) // use shift algorithm to shift only letters (both uppercase and lowercase) while leaving non-letter characters unchanged.
        else -> {
            println("Error: Unknown algorithm $alg")
            return
        }
    }

    // Handle Output
    if (outPath != null){
        try {
            File(outPath).writeText(result)
        } catch (e: Exception){
            println(e.message + "Error: Could not write to the output file.")
        }
    } else {
        println(result)
    }
}

/**
 * Shifting algorithm: shifts each letter by the specific number according to its order in the alphabet.
 * Only English letters (a-z, A-Z) are shifted, while non-letter characters remain unchanged.
 */
fun shiftAlgorithm(message: String, mode: String, key: Int): String {
    val shiftKey = if (mode == "dec") -key else key // if mode is "dec", we negate the key to shift in the opposite direction for decryption.
    val result = StringBuilder()  // use StringBuilder for efficient string concatenation.

    // use a for loop to iterate through each character in the message and apply the appropriate shift based on whether it's an uppercase letter, lowercase letter, or non-letter character.
    for (char in message){
        when(char) {
            in 'a'..'z' -> {
                val shifted = (char - 'a' + shiftKey) % 26 // calculate the new position by adding the shift key and using modulo to wrap around the alphabet.
                val finalChar = if (shifted < 0) shifted + 26 else shifted // if the result is negative, we add 26 to wrap around correctly.
                result.append(('a'.code + finalChar).toChar()) // convert back to character and append to result.
            }
            in 'A'..'Z' -> {
                val shifted = (char - 'A' + shiftKey) % 26 // similar logic for uppercase letters.
                val finalChar = if (shifted < 0) shifted + 26 else shifted
                result.append(('A'.code + finalChar).toChar()) // convert back to character and append to result.
            }
            else -> result.append(char) // non-letter characters are appended unchanged.
        }
    }
    return result.toString() // convert StringBuilder to String and return the final result.
}

/**
 * Unicode algorithm: shifts each character based on the Unicode table.
 */
fun unicodeAlgorithm(message: String, mode: String, key: Int): String {
    val shiftKey = if (mode == "dec") -key else key // determine the shift direction based on the mode.
    val result = StringBuilder() // use StringBuilder for efficient string concatenation.

    // iterate through each character in the message, shift it by the specified key, and append the shifted character to the result.
    for (char in message){
        val shiftedChar = char + shiftKey // shift the character by adding the key to its Unicode value.
        result.append(shiftedChar) // append the shifted character to the result.
    }
    return result.toString() // convert StringBuilder to String and return the final result.
}

