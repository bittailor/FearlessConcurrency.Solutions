
#include <thread>
#include <gtest/gtest.h>

#include "prime.hpp"

constexpr uint32_t UP_TO = 200000;
constexpr uint32_t PRIMES = 17984;

TEST(IsPrime, UpTo_70) {
  std::set<uint32_t> primes {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67};
  for (size_t i = 2; i < 70; i++)
  {
    EXPECT_EQ(primes.find(i)!=primes.end(), is_prime(i));
  }
}

TEST(CountPrimes, One_Thread) {
  EXPECT_EQ(PRIMES, count_primes(UP_TO,1));
}

TEST(CountPrimes, Two_Thread) {
  EXPECT_EQ(PRIMES, count_primes(UP_TO,2));
}

TEST(CountPrimes, Four_Thread) {
  EXPECT_EQ(PRIMES, count_primes(UP_TO,4));
}

TEST(CountPrimes, Max_Thread) {
  auto maxThreads = std::thread::hardware_concurrency();
  std::cout << "max threads is " << maxThreads << std::endl;
  EXPECT_EQ(PRIMES, count_primes(UP_TO, maxThreads));
}
