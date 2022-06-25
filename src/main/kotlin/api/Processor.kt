package api

import model.Message

interface Processor {
    fun process()
    fun processError(message: Message): Message
    fun processCountOfProduct(message: Message): Message
    fun processMinusProduct(message: Message): Message
    fun processPlusProduct(message: Message): Message
    fun processAddGroup(message: Message): Message
    fun processAddProductToGroup(message: Message): Message
    fun processPriceToProduct(message: Message): Message
}
