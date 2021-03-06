#!/usr/bin/env groovy
/**
 * Messing about with closures in Groovy
 *
 * @author Warwick Hunter
 * @since  2011-03-27
 */
def benchmark(repeat, Closure worker) {
    start = System.currentTimeMillis()
    repeat.times{worker(it)}
    stop = System.currentTimeMillis()
    return stop - start
}
slow = benchmark(10000) { (int) it / 2 }
fast = benchmark(10000) { it.intdiv(2) }
assert fast < slow
printf "fast=%d slow=%d ratio=%f %n", fast, slow, fast/slow