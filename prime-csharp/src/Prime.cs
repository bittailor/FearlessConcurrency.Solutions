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
        uint currentNumber = 2;
        for (uint t = 0; t < numberOfThreads; t++) {
            var thread = new Thread(() => {
                while(true){
                    uint localCurrentNumber = Interlocked.Increment(ref currentNumber) - 1;  
                    if(localCurrentNumber >= upTo) {
                        break;
                    }
                    if (IsPrime(localCurrentNumber)) {
                        Interlocked.Increment(ref numberOfPrimeNumbers); 
                    }   
                }
            });    
            threads.Add(thread);
            thread.Start();
        }
        threads.ForEach(t => t.Join());
        return Interlocked.Exchange(ref numberOfPrimeNumbers, 0);
    }

}









