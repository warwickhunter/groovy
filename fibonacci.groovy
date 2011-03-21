#!/usr/bin/env groovy
// Fibonacci sequence from the book Groovy in Action 2011-03-21
current = 1 
next = 1
count = 0
20.times {
    printf "%8d ", current
    newCurrent = next
    next = next + current
    current = newCurrent
    if (++count % 10 == 0) {
        println ''
    } 
}
println ''