using System.Collections.Generic;

namespace Prime;

public static class Prime {
    
    public static bool IsPrime(uint n) {
        for (uint i = 2; i <= n / 2; ++i) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static uint CountPrimes(uint upTo, uint numberOfThreads) {
        List<Thread> threads = new();
        uint numberOfPrimeNumbers = 0;
        object numberOfPrimeNumbersLock = new();
        uint currentNumber = 2;
        object currentNumberLock = new();
        for (uint t = 0; t < numberOfThreads; t++) {
            var thread = new Thread(() => {
                while(true){
                    uint localCurrentNumber;
                    lock (currentNumberLock) {
                        localCurrentNumber = currentNumber++;
                    }  
                    if(localCurrentNumber >= upTo) {
                        break;
                    }
                    if (IsPrime(localCurrentNumber)) {
                        lock (numberOfPrimeNumbersLock) {
                            ++numberOfPrimeNumbers;
                        }  
                    }   
                }
            });    
            threads.Add(thread);
            thread.Start();
        }
        threads.ForEach(t => t.Join());

        lock (numberOfPrimeNumbersLock) {
            return numberOfPrimeNumbers;
        }
    }

}









