#!/usr/bin/env groovy
package basics
/**
 * Groovy sample from "Groovy in Action" book. Using CliBuilder to handle command line arguments
 * 
 * @author Warwick Hunter
 * @since  2011-08-27
 */

def cli = new CliBuilder()
cli.h(longOpt:'help', 'Usage information')
cli.f(longOpt:'file', 'Input file', required:true, args:1, type:String)

def options = cli.parse(args)
if (!options) {
    return
}
if (options.h) {
    cli.usage()
}
def verbs = options.arguments().join(' ')
println "Do something to file $options.f with $verbs"

