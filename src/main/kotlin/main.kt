const val MINIMUM_COMMISSION = 35_00.0
const val TRANSFER_FEE = (0.75 / 100)

fun main() {
    println("Добро пажаловать в \"переводы ВКонтакте\"")
    println("Комиссия за перевод 0.75% от суммы перевода, минимум 35 руб.")
    print("Введите сумму перевода: ")
    val amount = readLine()?.toInt() ?: return
    val amountKop = amount * 100
    var commission = amountKop * TRANSFER_FEE
    if (commission <= MINIMUM_COMMISSION) commission = MINIMUM_COMMISSION
    val outputRub = (commission / 100).toInt()
    val outputKopek = (commission % 100).toInt()
    print("Комиссия за перевод $outputRub руб. $outputKopek коп.")
}




