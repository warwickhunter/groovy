#!/usr/bin/env groovy
/**
 * Markup builder example from "Groovy in Action"
 *
 * @author Warwick Hunter
 * @since  2011-04-10
 */
def builder = new groovy.xml.MarkupBuilder()
builder.numbers {
    description 'Squares and factors of 10..15'
    for (i in 10..15) {
        number (value: i, square: i*i) {
            for (j in 2..<i) {
                if (i % j == 0) {
                    factor (value: j)
                }
            }
        }
    }
}