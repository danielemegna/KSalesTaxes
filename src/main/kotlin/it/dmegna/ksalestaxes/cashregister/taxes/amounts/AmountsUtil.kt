package it.dmegna.ksalestaxes.cashregister.taxes.amounts

internal class AmountsUtil {
    companion object {
        internal fun normalize(value: Double) = "%.4f".format(value).toDouble()
    }
}
