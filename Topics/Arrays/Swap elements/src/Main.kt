fun main() {    
    val numbers = readLine()!!.split(' ').map { it.toInt() }.toIntArray()
    // Do not touch lines above
    // Write only exchange actions here.
    var temp = numbers[0] // get first element
    var temp1 = numbers[numbers.size - 1] // get last element
    // swap first and last elements
    // assign last element to first element
    numbers[0] = temp1
    // assign first element to last element
    numbers[numbers.size - 1] = temp



    // Do not touch lines below
    println(numbers.joinToString(separator = " "))
}