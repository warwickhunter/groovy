#!/usr/bin/env groovy
// Groovy sample from Groovy in Action book
def count = 100
new File('..').eachFileRecurse { 
    if (!it.directory) {
        printf "%06d %s %n", it.length(), it.absolutePath 
        count--
    }
    if (count <= 0) {
        return;
    }
}
