import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

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
        //arrange
        val paymentCardType = 1
        val amountTransferKop = 151_000_00
        val amountOfPastTransfersKop = 600_000_00
        //act
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        //assert
        assertEquals(false, result)
    }

    @Test
    fun checkingLimitsVisaMirTrue() {
        //arrange
        val paymentCardType = 2
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
    fun checkingLimitsVisaMirFalse() {
        //arrange
        val paymentCardType = 2
        val amountTransferKop = 151_000_00
        val amountOfPastTransfersKop = 600_000_00
        //act
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        //assert
        assertEquals(false, result)
    }

    @Test
    fun checkingLimitsVKPayTrue() {
        //arrange
        val paymentCardType = 3
        val amountTransferKop = 15_000_00
        val amountOfPastTransfersKop = 40_000_00
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
    fun checkingLimitsVKPayFalse() {
        //arrange
        val paymentCardType = 3
        val amountTransferKop = 16_000_00
        val amountOfPastTransfersKop = 40_000_00
        //act
        val result = checkingLimits(
            paymentCardType = paymentCardType,
            amountTransferKop = amountTransferKop,
            amountOfPastTransfersKop = amountOfPastTransfersKop
        )
        //assert
        assertEquals(false, result)
    }

    @Test
    fun transferFeeMastercardMaestro() {
        val paymentCardType = 1
        val amountTransferKop = 10_000_00
        val amountOfPastTransfersKop = 7500000


        val result = transferFee(
            paymentCardType = paymentCardType,
            amountOfPastTransfersKop = amountOfPastTransfersKop,
            amountTransferKop = amountTransferKop
        )



        assertEquals(8_000.0, result, 8_000.0)
    }
}