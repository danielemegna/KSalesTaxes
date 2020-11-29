package it.dmegna.ksalestaxes.shoppingbasket

import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.NetPrice
import it.dmegna.ksalestaxes.shoppingbasket.taxes.amounts.TaxAmount

data class ShoppingBasketItemWithTax(
    val qty: Int,
    val description: String,
    val unitNetPrice: NetPrice,
    val unitTaxAmount: TaxAmount
) {
    fun taxedPrice() = (this.unitNetPrice + this.unitTaxAmount) * this.qty
    fun taxAmount() = this.unitTaxAmount * this.qty
}
