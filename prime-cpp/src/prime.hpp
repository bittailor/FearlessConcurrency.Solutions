#pragma once

#include <cstdint>

bool is_prime(uint32_t n);
uint32_t count_primes_no_threads(uint32_t max);
uint32_t count_primes(uint32_t max, uint32_t numberOfThreads);
