[TestFixture]
public class PrimeFixture
{


    const uint UP_TO = 200000;
    const uint PRIMES = 17984;

    [Test]
    public void CountPrimes_OneThread()
    {
        Assert.That(Prime.CountPrimes(UP_TO, 1), Is.EqualTo(PRIMES));
    }

    [Test]
    public void CountPrimes_TwoThread()
    {
        Assert.That(Prime.CountPrimes(UP_TO, 2), Is.EqualTo(PRIMES));
    }

    [Test]
    public void CountPrimes_FourThread()
    {
        Assert.That(Prime.CountPrimes(UP_TO, 4), Is.EqualTo(PRIMES));
    }

    [Test]
    public void CountPrimes_MaxThread()
    {
        var maxThreads = Convert.ToUInt32(Environment.ProcessorCount);
        Assert.That(Prime.CountPrimes(UP_TO, maxThreads), Is.EqualTo(PRIMES), $"max threads is {maxThreads}");
    }
}