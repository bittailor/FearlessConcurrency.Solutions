use prime_rust::count_primes;

fn main() {
    const UP_TO: u32 = 200000;

    let mut number_of_threads = 1;

    let args: Vec<String> = std::env::args().collect();
    if args.len() > 1 {
        number_of_threads = args[1].parse().expect("parameter must be a number");
    }

    println!("Run with {number_of_threads} threads up to {UP_TO}");
    let count = count_primes(UP_TO, number_of_threads);
    println!("Found {} prime numbers up to {}", count, UP_TO);
}
