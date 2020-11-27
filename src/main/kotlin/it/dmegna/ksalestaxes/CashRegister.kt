package it.dmegna.ksalestaxes

class CashRegister {

    fun receiptFor(shoppingBasket: ShoppingBasket): Receipt {
        return Receipt(
            items = receiptItemsFrom(shoppingBasket),
            salesTaxes = 0.0
        )
    }

    private fun receiptItemsFrom(shoppingBasket: ShoppingBasket): List<Receipt.Item> {
        return shoppingBasket.items.map {
            Receipt.Item(it.qty, it.description, it.unitNetPrice * it.qty)
        }
    }
}
