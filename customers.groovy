#!/usr/bin/env groovy
def customers = new XmlSlurper().parse(new File('customers.xml'))
for (customer in customers.corporate.customer) {
    println "${customer.@name} works for ${customer.@company}"
}
for (customer in customers.consumer.customer) {
    println "${customer.@name} is a consumer"
}
