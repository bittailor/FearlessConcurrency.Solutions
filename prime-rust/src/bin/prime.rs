use prime_rust::count_prime;

fn main() {
    const MAX: u32 = 200000;

    let mut number_of_threads = 1;

    let args: Vec<String> = std::env::args().collect();
    if args.len() > 1 {
        number_of_threads = args[1].parse().expect("parameter must be a number");
    }

    println!("Run with {number_of_threads} threads up to {MAX}");
    let count = count_prime(MAX, number_of_threads);
    println!("Found {} prime numbers up to {}", count, MAX);
}
