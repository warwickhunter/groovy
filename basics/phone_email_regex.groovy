#!/usr/bin/env groovy
/**
 * Check that my understanding of regular expression language is correct
 * 
 * @author Warwick Hunter
 * @since  2011-03-23
 */

// Test phone number validation
ph1 = "+61412421576"
ph2 = "61412421576"
ph3 = "+6141242157699999999999999999999999999999"
ph4 = "+61412421576999999999999999999999999999999"

assert ph1.length() < 40
assert ph2.length() < 40
assert ph3.length() == 41
assert ph4.length() > 41

pattern = "\\A\\+\\d{1,40}\\z"

println "phone pattern $pattern"
for (ph in [  ph1, ph2, ph3, ph4 ]) {
    if (ph ==~ pattern) {
        println "match $ph"
    } else {
        println "nomatch $ph"
    }
}

// Test email address validation
email1 = "w.hunter@computer.org"
email2 = "w@hunter@computer.org"
email3 = "w.hunterATcomputer.org"
email4 = "".padLeft(100, 'a') + "@" +  "".padRight(100, 'b')
assert email4.length() == 201
email5 = "Z" + email4
assert email5.length() == 202
email6 = email4 + "X"
assert email6.length() == 202

pattern = "\\A[^@]{1,100}@[^@]{1,100}\\z"

println "email pattern $pattern"
for (ph in [ email1, email2, email3, email4, email5, email6 ]) {
    if (ph ==~ pattern) {
        println "match $ph"
    } else {
        println "nomatch $ph"
    }
}
