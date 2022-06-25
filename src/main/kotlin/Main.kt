import impl.*

fun main() {
    ReceiverImpl().receiveMessage()
    DescriptorImpl().description()
    ProcessorImpl().process()
    EncryptorImpl().encrypt()
    SenderImpl().send()
}
