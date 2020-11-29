package it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts

internal class AmountsUtil {
    companion object {
        internal fun normalize(value: Double) = "%.4f".format(value).toDouble()
    }
}
