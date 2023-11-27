package prime.kotlin

import kotlin.concurrent.thread

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
    var numberOfPrimeNumbers = 0u
    val numberOfPrimeNumbersLock = Any()
    var currentNumber = 2u
    val currentNumberLock = Any()

    for (t in 0u..<numberOfThreads) {
        threads.add(thread {
            while(true){
                var localCurrentNumber : UInt
                synchronized(currentNumberLock) {
                    localCurrentNumber = currentNumber++    
                }
                if(localCurrentNumber >= upTo) {
                    break;
                }
                if (isPrime(localCurrentNumber)) {
                    synchronized(numberOfPrimeNumbersLock) {
                        ++numberOfPrimeNumbers
                    }
                }    
            }
        });   
    }    

    for (t in threads) {
        t.join();
    }

    synchronized(numberOfPrimeNumbersLock) {
        return numberOfPrimeNumbers
    }
}