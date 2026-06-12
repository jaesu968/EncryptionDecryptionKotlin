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

## Key Concepts
- **Encryption:** Converting plaintext into ciphertext.
- **Decryption:** Converting ciphertext back into original plaintext.
- **Key:** A parameter that determines the output of the encryption/decryption algorithm.
- **Unicode:** Using character codes for shifting allows for a more universal encryption method that covers more than just the English alphabet.

## Code Snippets (Stage 3 Implementation)

### Encryption Logic
```kotlin
fun encryption(message: String, key: Int) {
    for (char in message) {
        val shiftedChar = (char.code + key).toChar()
        print(shiftedChar)
    }
}
```

### Decryption Logic
```kotlin
fun decryption(message: String, key: Int) {
    for (char in message) {
        val shiftedChar = (char.code - key).toChar()
        print(shiftedChar)
    }
}
```

## How to Run
The program expects three lines of input:
1. Operation (`enc` or `dec`).
2. The message/ciphertext.
3. The key (integer).
