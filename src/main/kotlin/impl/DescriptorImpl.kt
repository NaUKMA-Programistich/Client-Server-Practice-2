package impl

import api.Descriptor
import model.Packet
import utils.QueueExtensions.takeIn
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

class DescriptorImpl : Descriptor {

    companion object {
        val inputPacketQueue: BlockingQueue<Packet> = ArrayBlockingQueue(10)
    }

    override fun description() {
        Thread {
            try {
                val inputBytes = ReceiverImpl.inputBytesQueue.takeIn()
                inputBytes?.let {
                    val packet = Packet(inputBytes)
                    println("Input-packet:$packet")
                    inputPacketQueue.put(packet)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }
}
