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