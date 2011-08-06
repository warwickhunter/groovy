#!/usr/bin/env groovy
/**
 * Experiment with the Groovy classes that allow Groovy to parse XML
 * 
 * @author Warwick Hunter
 * @since 2011-08-06
 */
package xml

// Use the XmlSlurper
def customers = new XmlSlurper().parse(new File('customers.xml'))
println "Corporate customers"
for (customer in customers.corporate.customer) {
    println "    ${customer.@name} works for ${customer.@company}"
}
println "Consumers"
for (customer in customers.consumer.customer) {
    printf '    %s%n', customer.@name
}

// Use the DOMBuilder
def doc = groovy.xml.DOMBuilder.parse(new FileReader('customers.xml'))
def root = doc.documentElement
def corporate = root.childNodes.find { 'corporate' == it.nodeName }
corporate.childNodes.grep { 'customer' == it.nodeName }.each { 
    def atts = it.attributes
    printf "Corporate: %s from %s%n", atts.getNamedItem('name').nodeValue, atts.getNamedItem('company').nodeValue 
}
def consumer = root.childNodes.find { 'consumer' == it.nodeName }
consumer.childNodes.grep { 'customer' == it.nodeName }.each { printf "Consumer: %s%n", it.attributes.getNamedItem('name').nodeValue }

// Now use DOMCategory
use(groovy.xml.dom.DOMCategory) {
    assert 'customers' == root.nodeName
    assert 'corporate' == root[0].nodeName
    assert 'consumer'  == root[1].nodeName
}

// Use Groovy's XML parser
doc = new XmlParser().parse(new File('customers.xml'))
assert 'customers'      == doc.name()
assert 'corporate'      == doc.corporate[0].name()
assert 'customer'       == doc.corporate[0].customer[0].name()
assert 'Warwick Hunter' == doc.corporate[0].customer[2].'@name'
assert 'Promptu'        == doc.corporate[0].customer[2].'@company'
assert 'consumer'       == doc.consumer[0].name()
assert 'customer'       == doc.consumer[0].customer[0].name()
assert 'Jane Doe'       == doc.consumer[0].customer[1].'@name'
