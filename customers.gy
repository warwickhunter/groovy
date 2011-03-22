#!/usr/bin/env groovy
def customers = new XmlSlurper().parse(new File('customers.xml'))
println "Corporate customers"
for (customer in customers.corporate.customer) {
    println "    ${customer.@name} works for ${customer.@company}"
}
println "Consumers"
for (customer in customers.consumer.customer) {
    printf '    %s%n', customer.@name
}