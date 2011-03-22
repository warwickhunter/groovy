#!/usr/bin/env groovy
// Messing about with Groovy lists and maps

// List
def roman = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII']
assert roman[4] == 'IV'
roman[9] = 'IX'
assert roman.size == 10
assert roman[8] == null

// Map
def http = [ 100 : 'continue', 200 : 'ok', 400: 'bad_request' ]
http[500] = 'internal server error'

println http

// Range
println 1..10

// Closure
http.each{ entry -> println entry }

// Control structures
def clinks = 0
for (remainingGuests in 0..9) {
    clinks += remainingGuests
}
assert clinks == (10*9)/2
println "clinks $clinks"
range = 0..9
for (j in range) {
    assert j == range[j]
}
