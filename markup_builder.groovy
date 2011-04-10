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

// HTML with MarkupBuilder
def writer = new FileWriter('markup.html')
def html = new groovy.xml.MarkupBuilder(writer)
html.html {
    head {
        title 'Constructed by MarkupBuilder'
    }
    body {
        h1 'What can I do with MarkupBuilder'
        form (action:'whatever') {
            for (line in ['Produce HTML','Produce XML','Have some fun']) {
                input(type:'checkbox', checked:'checked', id:line, '')
                label(for:line, line)
                br('')
            }
        }
    }
}