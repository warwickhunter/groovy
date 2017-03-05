#!/usr/bin/env groovy
/**
 * Mess around with Guava's Stopwatch
 * 
 * @author Warwick Hunter
 * @since 2014-10-27
 */
package basics

// Use Grape to handle the downloading of required jars, nice!
@Grapes(
    @Grab(group='com.google.guava', module='guava', version='19.0')
)

import com.google.common.base.*
import java.util.concurrent.*

Stopwatch sw = Stopwatch.createUnstarted()
sw.start()
Thread.sleep(1021)
sw.stop()
sw.elapsed(TimeUnit.SECONDS)
println "Elapsed=" + sw
printf "Elapsed=%d ms%n", sw.elapsed(TimeUnit.MILLISECONDS)
sw.elapsed(TimeUnit.MILLISECONDS)
println "Elapsed=" + sw

return 0