package it.dmegna.ksalestaxes.taxes.data

internal class DataUtils {
    companion object {
        internal fun normalize(value: Double) = "%.2f".format(value).toDouble()
    }
}
