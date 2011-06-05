#!/usr/bin/env groovy
/* 
 * File I/O experiments inspired by the Groovy in Action book
 * 
 * @author Warwick Hunter
 * @since  2011-06-05
 */
println "/etc/fstab"
fstab = new File("/etc/fstab")
fstab.splitEachLine(/\s/) {
    if (!(it[0] ==~ "^#.*")) {
        for (field in it) {
            printf "%s ", field
        }
        println ""
    } 
}
println ""
println "type 1"
hex = []
cwd = new File("/bin/cat")
cwd.eachByte { hex << it }
assert hex.size() == cwd.length()
count = 0
for (b in hex) {
    printf "0x%02x ", b
    if (++count % 20 == 0) {
        println ""
    }
    if (count > 100) {
        break;
    }
}
println ""
println "type 2"
count = 0
cwd.eachByte { b ->
    if (count > 100) {
        // I don't believe there is a way of stopping a closure loop
        return;
    }
    printf "0x%02x ", b
    if (++count % 20 == 0) {
        println ""
    }
}
