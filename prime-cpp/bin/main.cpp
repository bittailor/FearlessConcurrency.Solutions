#include <iostream>

#include "prime.hpp"

int main(int argc, char* argv[]) {
    constexpr uint32_t UP_TO = 200000;

    uint32_t numberOfThreads = 1;

    if (argc > 1) {
        numberOfThreads = std::stoi(argv[1]);
    }

    std::cout << "Run with " << numberOfThreads << " threads up to " << UP_TO << std::endl;
    uint32_t count = count_primes(UP_TO, numberOfThreads);
    std::cout << "Found " << count <<" prime numbers up to" << UP_TO << std::endl;    
    
    
    return 0;
}