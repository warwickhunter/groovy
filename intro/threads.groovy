#!/usr/bin/env groovy
/*
 * Thread experiments inspired by the Groovy in Action book
 *
 * @author Warwick Hunter
 * @since  2011-06-06
 */

// Threads and the use of closures
Thread.start { for (i in 1..10) { println i } }
for (i in 11..20) { println i }
Thread.start { sleep 1000; println "cookoo" }

// Processes can be created from strings
proc = "ls -lh".execute()
proc.inputStream.eachLine { println it }

// Do the same thing with ant
groovyHome = System.getenv('GROOVY_HOME')
new File("$groovyHome/lib").eachFileMatch(~/ant.*\.jar/, {
    this.class.classLoader.rootLoader.addURL(new URL("file:$it"))
})
ant = new AntBuilder()
ant.exec(
    dir            : '.',
    executable     : 'ls',
    outputproperty : 'lsout',
    errorproperty  : 'lserr',
    resultproperty : 'lsres') {
        arg(line : '-lh /etc')
    }
println ant.project.properties.lsres
println ant.project.properties.lsout

