#!/usr/bin/env groovy
/**
 * Read a file containing many certificate and separate each 
 * certificate into a separate file
 * 
 * @author Warwick Hunter 
 * @since  2011-03-22
 */
def collecting = false
def outfileCount = 0
new File('ca-bundle.crt').eachLine { line ->
    if (line =~ "BEGIN") {
        outfile = new File(String.format('certs/ca-bundle-%d.crt', outfileCount++))
        println outfile.name
        outfile.append("$line\n")
        collecting = true
    } else if (line =~ "END") {
        outfile.append("$line\n")
        collecting = false
    } else if (collecting) {
        outfile.append("$line\n")
    }
}
