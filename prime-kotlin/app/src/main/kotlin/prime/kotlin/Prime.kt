package prime.kotlin

import kotlin.concurrent.thread
import java.util.concurrent.atomic.AtomicInteger

fun isPrime(n: UInt): Boolean {
    val end = (n / 2u)
    for (i in 2u..end) {
        if ((n % i) == 0u) {
            return false
        }
    }
    return true    
}

fun countPrimes(upTo: UInt, numberOfThreads: UInt): UInt {
    var threads = mutableListOf<Thread>()
    var numberOfPrimeNumbers = AtomicInteger(0)
    var currentNumber =  AtomicInteger(2)
    
    for (t in 0u..< numberOfThreads) {
        threads.add(thread {
            while(true){
                var localCurrentNumber = currentNumber.getAndAdd(1).toUInt();
                if(localCurrentNumber >= upTo) {
                    break;
                }
                if (isPrime(localCurrentNumber.toUInt())) {
                    numberOfPrimeNumbers.incrementAndGet()
                }    
            }
        });    
    }    

    for (t in threads) {
        t.join();
    }

    return numberOfPrimeNumbers.get().toUInt()
}