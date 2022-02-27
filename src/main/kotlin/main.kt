fun main() {
    val amount = 25500000
    val minimumCommission = 3500
    val transferFee = 75
    val commission: Int
    val rub: Int
    val kopek: Int

    if ((amount * transferFee / 10000) <= minimumCommission) {
        commission = minimumCommission
    } else {
        commission = amount * transferFee / 10000
    }
    rub = commission / 100
    kopek = commission - (rub * 100)
        println("Комиссия за перевод: " + rub + "руб. " + kopek + " коп.")
}




