package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.cashregister.Receipt

interface ReceiptFormatter<T> {
    fun format(receipt: Receipt): T

}
