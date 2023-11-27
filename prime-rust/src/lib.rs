pub fn is_prime(n: u32) -> bool {
    for i in 2..=(n / 2) {
        if n % i == 0 {
            return false;
        }
    }
    true
}

pub fn count_primes(up_to: u32, number_of_threads: usize) -> u32 {
    let number_of_prime_numbers = std::sync::Mutex::new(0u32);
    let current_number = std::sync::Mutex::new(2u32);

    std::thread::scope(|scope| {
        for _t in 0..number_of_threads {
            scope.spawn(|| loop {
                let local_current_number = {
                    let mut current_number = current_number.lock().unwrap();
                    let local_current_number = *current_number;
                    *current_number += 1;
                    local_current_number
                };
                if local_current_number >= up_to {
                    break;
                }
                if is_prime(local_current_number) {
                    let mut number_of_prime_numbers = number_of_prime_numbers.lock().unwrap();
                    *number_of_prime_numbers = *number_of_prime_numbers + 1;
                }
            });
        }
    });

    let result = *number_of_prime_numbers.lock().unwrap();
    result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_is_prime() {
        let primes: Vec<u32> = vec![
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
        ];
        for i in 2..20 {
            assert_eq!(primes.contains(&i), is_prime(i), "{}", i);
        }
    }

    const UP_TO: u32 = 200000;
    const PRIMES: u32 = 17984;

    #[test]
    fn test_count_prime_one_thread() {
        assert_eq!(PRIMES, count_primes(UP_TO, 1));
    }

    #[test]
    fn test_count_prime_two_threads() {
        assert_eq!(PRIMES, count_primes(UP_TO, 2));
    }

    #[test]
    fn test_count_prime_four_threads() {
        assert_eq!(PRIMES, count_primes(UP_TO, 4));
    }

    #[test]
    fn test_count_prime_available_parallelism_threads() {
        let number_of_threads = std::thread::available_parallelism().unwrap().get();
        assert_eq!(PRIMES, count_primes(UP_TO, number_of_threads));
    }
}
