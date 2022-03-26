const val MINIMUM_COMMISSION_VISA_MIR = 35_00.0
const val TRANSFER_FEE_VISA_MIR = (0.75 / 100)
const val TRANSFER_FEE_MASTERCARD_MAESTRO = (0.6 / 100)
const val ADDITIONAL_TRANSFER_FEE_MASTERCARD_MAESTRO = 20_00.0
const val LIMIT_MASTERCARD_MAESTRO = 75_000_00.0
const val MAX_AMOUNT_TRANSFER_DAY_CARDS = 150_000_00
const val MAX_AMOUNT_TRANSFER_MONTH_CARDS = 600_000_00
const val MAX_AMOUNT_TRANSFER_VKPAY = 15_000_00
const val MAX_AMOUNT_TRANSFER_MONTH_VKPAY = 40_000_00


fun main() {
    println("Добро пажаловать в \"переводы ВКонтакте\"")
    print("Выберите тип карты/счета используемых для перевода:")
    print("Для Master Card и Maestro введите - 1, для Visa и Мир - 2, для VK Pay - 3: ")
    val paymentCardType = readLine()?.toInt() ?: return
    val outPaymentCardType = transferConditionsTypeOfCard(paymentCardType)

    print("Введите сумму предыдущих переводов в этом месяце:")
    val amountOfPastTransfers = readLine()?.toInt() ?: return

    print("Введите сумму перевода: ")
    val amountTransfer = readLine()?.toInt() ?: return

    val amountTransferKop = amountTransfer * 100
    val amountOfPastTransfersKop = amountOfPastTransfers * 100

    val limits = checkingLimits(paymentCardType, amountTransferKop, amountTransferKop)

    if (!limits) {
        println("Превышен лимит на перевод. Попробуйте другой способ перевода.")
        return
    }

    val commission = transferFee(paymentCardType, amountOfPastTransfersKop, amountTransferKop,)

    val outCommission = transformationCommissionOfText(commission)

    println("Спасибо за использование \"переводы ВКонтакте\".")
}

fun transferConditionsTypeOfCard(paymentCardType: Int) {
    when (paymentCardType) {
        1 -> {
            println("За переводы с карт Mastercard и Maestro комиссия не взимается при сумме перевода до 75 000 руб. ")
            println("в календарный месяц, в иных случаях - 0,6% + 20 руб. ")
            println("Максимальная сумма перевода 150 000 руб. в сутки и 600 000 руб. в месяц")
        }
        2 -> {
            println("Комиссия за переводы с карт Visa и МИР - 0,75% минимум 35 руб. Максимальная сумма перевода 150 000 руб. в сутки и 600 000 руб. в месяц")
        }
        else -> println(
            "Комиссия за переводы на счет VK Pay - не взимается. Максимальная сумма перевода 15 000 руб. " +
                    "за один раз и 40 000 руб. в месяц."
        )
    }
}

fun checkingLimits(paymentCardType: Int, amountTransferKop: Int, amountOfPastTransfersKop: Int): Boolean {
    return when {
        paymentCardType == 1 && amountTransferKop > MAX_AMOUNT_TRANSFER_DAY_CARDS
                || paymentCardType == 1 && amountOfPastTransfersKop > MAX_AMOUNT_TRANSFER_MONTH_CARDS -> false
        paymentCardType == 2 && amountTransferKop > MAX_AMOUNT_TRANSFER_DAY_CARDS
                || paymentCardType == 2 && amountOfPastTransfersKop > MAX_AMOUNT_TRANSFER_MONTH_CARDS -> false
        paymentCardType == 3 && amountTransferKop > MAX_AMOUNT_TRANSFER_VKPAY
                || paymentCardType == 3 && amountOfPastTransfersKop > MAX_AMOUNT_TRANSFER_MONTH_VKPAY -> false
        else -> {
            true
        }
    }
}

fun transferFee(paymentCardType: Int = 3, amountOfPastTransfersKop: Int = 0, amountTransferKop: Int,): Double {
    return when {
        paymentCardType == 1 && amountOfPastTransfersKop >= LIMIT_MASTERCARD_MAESTRO -> {
            amountTransferKop * TRANSFER_FEE_MASTERCARD_MAESTRO + ADDITIONAL_TRANSFER_FEE_MASTERCARD_MAESTRO
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
