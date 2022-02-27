import javax.swing.JOptionPane

fun main() {
    val amount = 25500000
    val minimumCommission = 3500
    val transferFee = 75
    val rub: Int
    val kopek: Int

    val commission: Int = if ((amount * transferFee / 10000) <= minimumCommission) minimumCommission
    else amount * transferFee / 10000

    rub = commission / 100
    kopek = commission - (rub * 100)
        println("Комиссия за перевод: " + rub + "руб. " + kopek + " коп.")
}




