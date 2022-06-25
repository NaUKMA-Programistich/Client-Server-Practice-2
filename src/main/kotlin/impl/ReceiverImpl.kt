package impl

import api.Receiver
import utils.Mock.bytes
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import kotlin.random.Random

class ReceiverImpl : Receiver {

    companion object {
        val inputBytesQueue: BlockingQueue<ByteArray> = ArrayBlockingQueue(10)
    }

    override fun receiveMessage() {
        val randomIndex = Random.nextInt(bytes.size)
        val randomPacket = bytes[randomIndex]
        Thread {
            try {
                println("Input-bytes:${randomPacket.contentToString()}")
                inputBytesQueue.put(randomPacket)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }
}
