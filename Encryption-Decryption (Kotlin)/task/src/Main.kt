package encryptdecrypt

import java.io.File


fun main(args: Array<String>) {

    // initialize default values for mode, key, and data, inPath and outPath
    var mode = "enc"
    var key = 0
    var data: String? = null // initialize as a nullable string since it might be empty or not present.
    var inPath: String? = null // initialize this to get the file path for input if -in is present
    var outPath: String? = null // initialize this to get the file path for output if -out is present

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

    // Process Data
    val result = when(mode){
        "dec" -> processData(finalData, -key) // decryption is done by shifting characters in the opposite direction, hence the negative key.
        else -> processData(finalData, key) // encryption is done by shifting characters forward using the positive key.
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
 * Shifts each character in the message by the given key.
 * Positive key for encryption, negative key for decryption.
 */
fun processData(message: String, key: Int): String {
    val result = StringBuilder() // Use StringBuilder for efficient string concatenation
    // if key is positive, it will shift characters forward (encryption)
    // if key is negative, it will shift characters backward (decryption)
    for (char in message){
        result.append((char.code + key).toChar())
    }
    return result.toString()
}

