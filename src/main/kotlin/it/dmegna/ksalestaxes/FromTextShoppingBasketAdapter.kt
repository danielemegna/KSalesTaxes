package it.dmegna.ksalestaxes

import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasket
import it.dmegna.ksalestaxes.shoppingbasket.inbound.ShoppingBasketAdapter

class FromTextShoppingBasketAdapter : ShoppingBasketAdapter<String> {
    override fun from(values: Iterable<String>): ShoppingBasket {
        return values
            .map(this::from)
            .let { ShoppingBasket(it) }
    }

    private fun from(stringValue: String): ShoppingBasket.Item {
        return VALUE_FORMAT_REGEX
            .toRegex()
            .find(stringValue)
            ?.let {
                val (qty, description, unitNetPrice) = it.destructured
                return ShoppingBasket.Item(
                    qty = qty.toInt(),
                    description = description,
                    unitNetPrice = unitNetPrice.toDouble()
                )
            }
            ?: throw ShoppingBasketAdapter.InvalidInputException(
                "Cannot adapt '$stringValue' to ShoppingBasket item"
            )
    }

    companion object {
        // <<(digits) (any string) "at" (digits.digits)>>
        private const val VALUE_FORMAT_REGEX = """^(\d+) (.+) at (\d+.\d+)$"""
    }
}