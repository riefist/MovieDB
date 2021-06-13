package id.aibangstudio.moviedb

import org.junit.Test


class RotateLeftTest {

    @Test
    fun rotateLeftTest() {
        rotateLeft(intArrayOf(1,2,3,4,5), 3)
    }

    private fun rotateLeft(arrayInt : IntArray, numOfRotation : Int) {
        val result = IntArray(arrayInt.size)
        val length = arrayInt.size
        for (i in arrayInt.indices) {
            result[(i + (length - numOfRotation)) % length] = arrayInt[i]
        }

        print(result.contentToString())
    }

}