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
    println("Выберите тип карты/счета используемых для перевода.")
    print("Введите, как указано далее: Mastercard, Maestro, Visa, Mir или VKPay: ")
    val paymentCardType = readLine()
    val outPaymentCardType = transferConditionsTypeOfCard(paymentCardType)
    println(outPaymentCardType)
    val paymentCardTypeForFunctions = typeOfCardForCalculatingFunctions(paymentCardType)
    print("Введите сумму предыдущих переводов в этом месяце:")
    val amountOfPastTransfers = readLine()?.toInt() ?: return

    print("Введите сумму перевода: ")
    val amountTransfer = readLine()?.toInt() ?: return

    val amountTransferKop = amountTransfer * 100
    val amountOfPastTransfersKop = amountOfPastTransfers * 100

    val limits = checkingLimits(paymentCardTypeForFunctions, amountTransferKop, amountTransferKop)

    if (!limits) {
        println("Превышен лимит на перевод. Попробуйте другой способ перевода.")
        return
    }

    val commission = transferFee(paymentCardTypeForFunctions, amountOfPastTransfersKop, amountTransferKop)

    val outCommission = transformationCommissionOfText(commission)

    println(outCommission)
    println("Спасибо за использование \"переводы ВКонтакте\".")
}

fun transferConditionsTypeOfCard(paymentCardType: String?): String {
    return when (paymentCardType) {
        "Mastercard", "Maestro" -> {
            "За переводы с карт Mastercard и Maestro комиссия не взимается\nпри сумме перевода до 75 000 руб. в календарный месяц, в иных случаях - 0,6% + 20 руб.\nМаксимальная сумма перевода 150 000 руб. в сутки и 600 000 руб. в месяц"
        }
        "Visa", "Mir" -> {
            "Комиссия за переводы с карт Visa и МИР - 0,75% минимум 35 руб. Максимальная сумма перевода 150 000 руб. в сутки и 600 000 руб. в месяц"
        }
        else ->
            "Комиссия за переводы на счет VK Pay - не взимается. Максимальная сумма перевода 15 000 руб.\nза один раз и 40 000 руб. в месяц."
    }
}

fun typeOfCardForCalculatingFunctions(paymentCardType: String?): String {
    return when (paymentCardType) {
        "Mastercard", "Maestro" -> "mastercard/maestro"
        "Visa", "Mir" -> "visa/mir"
        else -> "vkpay"
    }
}

fun checkingLimits(paymentCardTypeForFunctions: String, amountTransferKop: Int, amountOfPastTransfersKop: Int): Boolean {
    return when {
        paymentCardTypeForFunctions == "mastercard/maestro" && amountTransferKop > MAX_AMOUNT_TRANSFER_DAY_CARDS
                || paymentCardTypeForFunctions == "mastercard/maestro" && amountOfPastTransfersKop > MAX_AMOUNT_TRANSFER_MONTH_CARDS -> false
        paymentCardTypeForFunctions == "visa/mir" && amountTransferKop > MAX_AMOUNT_TRANSFER_DAY_CARDS
                || paymentCardTypeForFunctions == "visa/mir" && amountOfPastTransfersKop > MAX_AMOUNT_TRANSFER_MONTH_CARDS -> false
        paymentCardTypeForFunctions == "vkpay" && amountTransferKop > MAX_AMOUNT_TRANSFER_VKPAY
                || paymentCardTypeForFunctions == "vkpay" && amountOfPastTransfersKop > MAX_AMOUNT_TRANSFER_MONTH_VKPAY -> false
        else -> {
            true
        }
    }
}

fun transferFee(
    paymentCardTypeForFunctions: String = "vkpay",
    amountOfPastTransfersKop: Int = 0,
    amountTransferKop: Int
): Double {
    return when {
        paymentCardTypeForFunctions == "mastercard/maestro" && amountOfPastTransfersKop >= LIMIT_MASTERCARD_MAESTRO -> {
            amountTransferKop * TRANSFER_FEE_MASTERCARD_MAESTRO + ADDITIONAL_TRANSFER_FEE_MASTERCARD_MAESTRO
        }
        paymentCardTypeForFunctions == "visa/mir" -> {
            var commissionVisaMir = amountTransferKop * TRANSFER_FEE_VISA_MIR
            if (commissionVisaMir <= MINIMUM_COMMISSION_VISA_MIR) commissionVisaMir = MINIMUM_COMMISSION_VISA_MIR
            return commissionVisaMir
        }
        else -> {
            0.0
        }
    }
}

fun transformationCommissionOfText(commission: Double): String {
    return if (commission != 0.0) {
        val outputRub = (commission / 100).toInt()
        val outputKopek = (commission % 100).toInt()
        "Комиссия за перевод $outputRub руб. $outputKopek коп."
    } else {
        "Комиссия за перевод 00 руб. 00 коп."
    }
}
