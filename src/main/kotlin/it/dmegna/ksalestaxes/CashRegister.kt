package it.dmegna.ksalestaxes

class CashRegister {

    fun receiptFor(shoppingBasket: ShoppingBasket): Receipt {
        val items = mutableListOf<Receipt.Item>()
        var salesTaxes = 0.0

        shoppingBasket.items.forEach {
            val unitItemTaxes = calculateUnitItemTaxesFor(it)
            val taxedUnitPrice = it.unitNetPrice + unitItemTaxes
            val taxedTotalPrice = taxedUnitPrice * it.qty
            items.add(Receipt.Item(it.qty, it.description, taxedTotalPrice))
            salesTaxes += unitItemTaxes * it.qty
        }

        return Receipt(items, salesTaxes)
    }

    private fun calculateUnitItemTaxesFor(it: ShoppingBasket.Item): Double {
        return 0.0
    }

}
