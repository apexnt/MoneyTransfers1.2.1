fun main() {
    val amount = 800000
    val minimumCommission = 3500
    val transferFee = 75
    val commission: Int
    if (amount <= 470000) {
        commission = minimumCommission
    } else{
        commission = amount * transferFee / 10000
    }
    println("Комиссия за перевод: " + commission + " коп.")
}