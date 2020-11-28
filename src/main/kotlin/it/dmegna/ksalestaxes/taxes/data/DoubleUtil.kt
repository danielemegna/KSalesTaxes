package it.dmegna.ksalestaxes.taxes.data

internal class DoubleUtil {
    companion object {
        internal fun normalize(value: Double) = "%.4f".format(value).toDouble()
    }
}
