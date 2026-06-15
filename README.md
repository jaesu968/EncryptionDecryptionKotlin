# Encryption-Decryption (Kotlin)

A simple Kotlin project that demonstrates various encryption and decryption techniques, from basic alphabet substitution to Unicode character shifting.

## Stages Overview

### Stage 1: Encrypted!
In this initial stage, the goal was to manually encrypt a specific message using a simple substitution cipher.
- **Concept:** Reverse alphabet substitution (a → z, b → y, c → x, ...).
- **Rule:** Only letters were replaced; spaces and punctuation marks remained unchanged.

### Stage 2: Knowledge is Key
Introduced the concept of a **Key** to control the encryption process.
- **Concept:** Caesar Cipher (Alphabet Shifting).
- **Behavior:** Each letter is shifted forward by a specified integer (the key). If the shift goes beyond 'z', it wraps around to 'a'.
- **Rule:** Only English letters were modified; non-letter characters were left as is.

### Stage 3: Decrypted!
Expanded the program to support both encryption and decryption using Unicode shifting.
- **Concept:** Unicode Shift Cipher.
- **Key Features:**
    - Supports both `enc` (encryption) and `dec` (decryption) operations.
    - Shifts all characters (including symbols and spaces) based on their Unicode values.
    - Modularized code using functions for better readability.

### Stage 4: I Command You
Transitioned from standard input to **command-line arguments** for better automation and flexibility.
- **Concept:** Command-line argument parsing.
- **Key Features:**
    - Supports `-mode`, `-key`, and `-data` arguments.
    - If a parameter is missing, the program uses default values (`enc`, `0`, and empty string).

### Stage 5: X-files
Added support for **File I/O**, allowing the program to read from and write to external files.
- **Concept:** File persistence.
- **Key Features:**
    - `-in` argument: Specifies the input file path to read the message from.
    - `-out` argument: Specifies the output file path to save the result.
    - Priority: `-data` has higher priority than `-in`.

### Stage 6: Choice, choice
Introduced multiple encryption **algorithms** and improved the project structure.
- **Concept:** Strategy Pattern (Choice of algorithms).
- **Key Features:**
    - `-alg` argument: Choose between `shift` and `unicode`.
    - **Shift Algorithm:** Shifts only English letters (a-z, A-Z) and wraps around; non-letter characters remain unchanged.
    - **Unicode Algorithm:** Shifts all characters based on their Unicode value.
    - Robust error handling for missing arguments or inaccessible files.

## Key Concepts
- **Encryption/Decryption:** Converting between plaintext and ciphertext.
- **Key:** An integer shift value determining the transformation.
- **Unicode vs. Alphabet Shift:** Understanding different ways to map character transformations.
- **Command-line Arguments:** Parsing flags like `-mode`, `-key`, etc., to configure program behavior dynamically.
- **File Handling:** Reading and writing data using `java.io.File`.

## Code Snippets (Final Implementation)

### Argument Parsing & Main Logic
```kotlin
fun main(args: Array<String>) {
    var mode = "enc"
    var key = 0
    var data: String? = null
    var inPath: String? = null
    var outPath: String? = null
    var alg = "shift"

    for (i in args.indices step 2) {
        when (args[i]) {
            "-mode" -> mode = args[i + 1]
            "-key" -> key = args[i + 1].toInt()
            "-data" -> data = args[i + 1]
            "-in" -> inPath = args[i + 1]
            "-out" -> outPath = args[i + 1]
            "-alg" -> alg = args[i + 1]
        }
    }
    // ... logic to determine data source and algorithm ...
}
```

### Shift Algorithm (Stage 6)
```kotlin
fun shiftAlgorithm(message: String, mode: String, key: Int): String {
    val shiftKey = if (mode == "dec") -key else key
    val result = StringBuilder()
    for (char in message) {
        when (char) {
            in 'a'..'z' -> {
                val shifted = (char - 'a' + shiftKey) % 26
                val finalChar = if (shifted < 0) shifted + 26 else shifted
                result.append(('a'.code + finalChar).toChar())
            }
            in 'A'..'Z' -> {
                val shifted = (char - 'A' + shiftKey) % 26
                val finalChar = if (shifted < 0) shifted + 26 else shifted
                result.append(('A'.code + finalChar).toChar())
            }
            else -> result.append(char)
        }
    }
    return result.toString()
}
```

## How to Run
The program is executed via command-line arguments:

```bash
java MainKt -mode enc -key 5 -data "Welcome to hyperskill!" -alg unicode
```

### Available Arguments:
- `-mode`: `enc` for encryption, `dec` for decryption (default: `enc`).
- `-key`: An integer shift value (default: `0`).
- `-data`: The message to process.
- `-in`: Path to a file containing the message (used if `-data` is absent).
- `-out`: Path to a file where the result will be saved (default: print to console).
- `-alg`: `shift` or `unicode` (default: `shift`).
