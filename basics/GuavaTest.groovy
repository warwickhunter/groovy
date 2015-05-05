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
    @Grab(group='com.google.guava', module='guava', version='14.0.1')
)

import com.google.common.base.*
import java.util.concurrent.*

Stopwatch sw = new Stopwatch()
sw.start()
Thread.sleep(1021)
sw.stop()
sw.elapsed(TimeUnit.SECONDS)
println "Elapsed=" + sw

return 0