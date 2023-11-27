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
    var threads = mutableListOf<Thread>();
    var numberOfPrimeNumbers = 0u;
    var currentNumber = 2u;

    for (t in 0u..<numberOfThreads) {
        threads.add(thread {
            while(true){
                val localCurrentNumber = currentNumber++; 
                if(localCurrentNumber >= upTo) {
                    break;
                }
                if (isPrime(localCurrentNumber)) ++numberOfPrimeNumbers;    
            }
        });   
    }    

    for (t in threads) {
        t.join();
    }

    return numberOfPrimeNumbers
}