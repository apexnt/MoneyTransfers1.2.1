const val MINIMUM_COMMISSION = 35_00U
const val TRANSFER_FEE = 75U

fun main() {
    println("Добро пажаловать в ''переводы ВКонтакте''")
    println("Комиссия за перевод 0.75%, минимум 35 руб.")
    print("Введите сумму перевода: ")
    val amount = readLine()?.toUInt() ?: return
    val amountKop = amount * 100u
    val commission: UInt = if ((amountKop * TRANSFER_FEE / 10000u) <= MINIMUM_COMMISSION) MINIMUM_COMMISSION
    else amountKop * TRANSFER_FEE / 10000u
    val outputRub = (commission / 100u).toInt()
    val outputKopek = (commission - ((outputRub * 100).toUInt())).toInt()
    print("Комиссия за перевод: $outputRub руб. $outputKopek коп.")
}




