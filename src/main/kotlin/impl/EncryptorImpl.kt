package impl

import api.Encryptor
import utils.QueueExtensions.takeIn
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class EncryptorImpl : Encryptor {

    companion object {
        val outputBytesQueue: BlockingQueue<ByteArray> = ArrayBlockingQueue(10)
    }

    override fun encrypt() {
        Thread {
            try {
                val outputPacket = ProcessorImpl.outputPacketQueue.takeIn()
                outputPacket?.let {
                    val bytes = outputPacket.convertToBytes()
                    println("Output-bytes:${bytes.contentToString()}")
                    outputBytesQueue.put(bytes)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }
}
