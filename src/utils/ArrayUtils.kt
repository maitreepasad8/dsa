package utils

fun printArray(array: IntArray) {
    for (i in array){
        print("$i ")
    }
    println()
}

fun swap(arr: IntArray, i : Int, j: Int) {
    val temp = arr[i]
    arr[i]=arr[j]
    arr[j]=temp
}
