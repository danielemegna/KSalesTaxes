package it.dmegna.ksalestaxes.shoppingbasket.outbound

interface ReceiptFormatter<T> {
    fun format(receipt: Receipt): T
}
