const uint UP_TO = 200000;
uint numberOfThreads = 1;

if (args.Length > 0) {
    numberOfThreads = uint.Parse(args[0]);
}

Console.WriteLine($"Run with {numberOfThreads} threads up to {UP_TO}");
var count = Prime.Prime.CountPrimes(UP_TO, numberOfThreads);
Console.WriteLine($"Found {count} prime numbers up to {UP_TO}");
