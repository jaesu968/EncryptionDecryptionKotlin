
// Write your code here. Do not import any libraries
val text = readLine()!! // read input string
val myFile = File("MyFile.txt") // file object
// duplicate it in the file MyFile.txt
myFile.writeText(text + text) // write the text to the file
