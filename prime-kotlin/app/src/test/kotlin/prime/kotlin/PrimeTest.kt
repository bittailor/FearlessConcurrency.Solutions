package prime.kotlin

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertEquals

const val UP_TO = 200000u;
const val PRIMES = 17984u;

class PrimeTest {
    
    @Test fun isPrime() {
        val primes = arrayOf(2u, 3u, 5u, 7u, 11u, 13u, 17u, 19u, 23u, 29u, 31u, 37u, 41u, 43u, 47u, 53u, 59u, 61u, 67u);
        for (i in 2u..<70u) {
            assertEquals(primes.contains(i), isPrime(i), "isPrime($i)")
        }
        assertNotNull(isPrime(UP_TO))
    }

    @Test fun countPrimes_OneThread() {
        assertEquals(PRIMES, countPrimes(UP_TO, 1u))
    }

    @Test fun countPrimes_TwoThread() {
        assertEquals(PRIMES, countPrimes(UP_TO, 2u))
    }

    @Test fun countPrimes_FourThread() {
        assertEquals(PRIMES, countPrimes(UP_TO, 4u))
    }

    @Test fun countPrimes_MaxThread() {
        val maxThread = Runtime.getRuntime().availableProcessors().toUInt()
        assertEquals(PRIMES, countPrimes(UP_TO, maxThread))
    }
}
