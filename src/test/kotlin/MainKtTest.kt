import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun transferConditionsTypeOfCardMastercardMaestro() {
        val paymentCardType = 1
        val result = transferConditionsTypeOfCard(
            paymentCardType = paymentCardType
        )
        assertEquals(
            "За переводы с карт Mastercard и Maestro комиссия не взимается\nпри сумме перевода до 75 000 руб. в календарный месяц, в иных случаях - 0,6% + 20 руб.\nМаксимальная сумма перевода 150 000 руб. в сутки и 600 000 руб. в месяц",
            result
        )
    }

    @Test
    fun transferConditionsTypeOfCardVisaMir() {
        val paymentCardType = 2
        val result = transferConditionsTypeOfCard(
            paymentCardType = paymentCardType
        )
        assertEquals(
            "Комиссия за переводы с карт Visa и МИР - 0,75% минимум 35 руб. Максимальная сумма перевода 150 000 руб. в сутки и 600 000 руб. в месяц",
            result
        )
    }

    @Test
    fun transferConditionsTypeOfCardVKPay() {
        val paymentCardType = 3
        val result = transferConditionsTypeOfCard(
            paymentCardType = paymentCardType
        )
        assertEquals(
            "Комиссия за переводы на счет VK Pay - не взимается. Максимальная сумма перевода 15 000 руб.\nза один раз и 40 000 руб. в месяц.",
            result
        )
    }


    @Test
    fun checkingLimitsMastercardMaestroTrue() {
        //arrange
        val paymentCardType = 1
        val amountTransferKop = 150_000_00
        val amountOfPastTransfersKop = 600_000_00
        //act
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        //assert
        assertEquals(true, result)
    }

    @Test
    fun checkingLimitsMastercardMaestroFalse() {
        val paymentCardType = 1
        val amountTransferKop = 151_000_00
        val amountOfPastTransfersKop = 600_000_00
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        assertEquals(false, result)
    }

    @Test
    fun checkingLimitsVisaMirTrue() {
        val paymentCardType = 2
        val amountTransferKop = 150_000_00
        val amountOfPastTransfersKop = 600_000_00
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        assertEquals(true, result)
    }

    @Test
    fun checkingLimitsVisaMirFalse() {
        val paymentCardType = 2
        val amountTransferKop = 151_000_00
        val amountOfPastTransfersKop = 600_000_00
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        assertEquals(false, result)
    }

    @Test
    fun checkingLimitsVKPayTrue() {
        val paymentCardType = 3
        val amountTransferKop = 15_000_00
        val amountOfPastTransfersKop = 40_000_00
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        assertEquals(true, result)
    }

    @Test
    fun checkingLimitsVKPayFalse() {
        val paymentCardType = 3
        val amountTransferKop = 16_000_00
        val amountOfPastTransfersKop = 40_000_00
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        assertEquals(false, result)
    }

    @Test
    fun transferFeeMastercardMaestrolimitExceeded() {
        val paymentCardType = 1
        val amountTransferKop = 10_000_00
        val amountOfPastTransfersKop = 75_000_00
        val result = transferFee(
            paymentCardType = paymentCardType,
            amountOfPastTransfersKop = amountOfPastTransfersKop,
            amountTransferKop = amountTransferKop
        )
        assertEquals(8_000.0, result, 8_000.0)
    }

    @Test
    fun transferFeeMastercardMaestrolimitNotExceeded() {
        val paymentCardType = 1
        val amountTransferKop = 10_000_00
        val amountOfPastTransfersKop = 74_000_00
        val result = transferFee(
            paymentCardType = paymentCardType,
            amountOfPastTransfersKop = amountOfPastTransfersKop,
            amountTransferKop = amountTransferKop
        )
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun transferFeeVisaMirMinCommission() {
        val paymentCardType = 2
        val amountTransferKop = 3_000_00
        val amountOfPastTransfersKop = 1_000
        val result = transferFee(
            paymentCardType = paymentCardType,
            amountOfPastTransfersKop = amountOfPastTransfersKop,
            amountTransferKop = amountTransferKop
        )
        assertEquals(35_00.0, result, 35_00.0)
    }

    @Test
    fun transferFeeVisaMirNotMinCommission() {
        val paymentCardType = 2
        val amountTransferKop = 10_000_00
        val amountOfPastTransfersKop = 1_000
        val result = transferFee(
            paymentCardType = paymentCardType,
            amountOfPastTransfersKop = amountOfPastTransfersKop,
            amountTransferKop = amountTransferKop
        )
        assertEquals(75_00.0, result, 75_00.0)
    }

    @Test
    fun transformationCommissionOfTextZero() {
        val commission = 0.0
        val result = transformationCommissionOfText(
            commission = commission
        )
        assertEquals("Комиссия за перевод 00 руб. 00 коп.", result)
    }

    @Test
    fun transformationCommissionOfTextNotZero() {
        val commission = 35_00.0
        val result = transformationCommissionOfText(
            commission = commission
        )
        assertEquals("Комиссия за перевод 35 руб. 0 коп.", result)
    }
}