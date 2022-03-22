const val MINIMUM_COMMISSION_VISA_MIR = 35_00.0
const val TRANSFER_FEE_VISA_MIR = (0.75 / 100)
const val TRANSFER_FEE_MASTERCARD_MAESTRO = (0.6 / 100)
const val LIMIT_MASTERCARD_MAESTRO = 75_000_00.0

fun main() {
    println("Добро пажаловать в \"переводы ВКонтакте\"")
    println("Выберите тип карты/счета используемых для перевода:")
    print("Для Master Card и Maestro введиете - 1, для Visa и Мир - 2, для VK Pay - 3: ")
    val paymentCardType = readLine()?.toInt() ?: return
    val outPaymentCardType = transferConditionsTypeOfCard(paymentCardType)

    print("Введите сумму предыдущих переводов в этом месяце:")
    val amountOfPastTransfers = readLine()?.toInt() ?: return

    print("Введите сумму перевода: ")
    val amountTransfer = readLine()?.toInt() ?: return

    val amountTransferKop = amountTransfer * 100
    val amountOfPastTransfersKop = amountOfPastTransfers * 100

    val commission = transferFee(paymentCardType, amountOfPastTransfersKop, amountTransferKop)

    val outCommission = transformationCommissionOfText(commission)

    println("Спасибо за использование \"переводы ВКонтакте\".")
}

fun transferConditionsTypeOfCard(paymentCardType: Int) {
    when (paymentCardType) {
        1 -> {
            println(
                "За переводы с карт Mastercard и Maestro комиссия не взимается при сумме перевода до 75 000 руб. " +
                        "в календарный месяц, в иных случаях - 0,6% + 20 руб."
            )
        }
        2 -> {
            println("Комиссия за переводы с карт Visa и МИР - 0,75% минимум 35 руб. ")
        }
        else -> println("Комиссия за переводы на счет VK Pay - не взимается")
    }

}

fun transferFee(paymentCardType: Int = 3, amountOfPastTransfersKop: Int = 0, amountTransferKop: Int): Double {
    return when {
        paymentCardType == 1 && amountOfPastTransfersKop >= LIMIT_MASTERCARD_MAESTRO -> {
            amountTransferKop * TRANSFER_FEE_MASTERCARD_MAESTRO + 20_00
        }
        paymentCardType == 2 -> {
            var commissionVisaMir = amountTransferKop * TRANSFER_FEE_VISA_MIR
            if (commissionVisaMir <= MINIMUM_COMMISSION_VISA_MIR) commissionVisaMir = MINIMUM_COMMISSION_VISA_MIR
            return commissionVisaMir
        }
        else -> {
            0.0
        }
    }
}

fun transformationCommissionOfText(commission: Double) {
    if (commission != 0.0) {
        val outputRub = (commission / 100).toInt()
        val outputKopek = (commission % 100).toInt()
        println("Комиссия за перевод $outputRub руб. $outputKopek коп.")
    } else {
        println("Комиссия за перевод 00 руб. 00 коп.")
    }
}