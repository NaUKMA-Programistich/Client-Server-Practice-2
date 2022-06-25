package impl

import api.Sender
import utils.QueueExtensions.takeIn

class SenderImpl : Sender {
    override fun send() {
        Thread {
            try {
                val outputBytes = EncryptorImpl.outputBytesQueue.takeIn()
                outputBytes?.let {
                    // send
                    println("Sender-bytes:${outputBytes.contentToString()}")
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }.start()
    }
}
