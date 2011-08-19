#!/usr/bin/env groovy
/**
 * Experiment with the Groovy classes that allow Groovy to use XPath to handle XML 
 * 
 * @author Warwick Hunter
 * @since 2011-08-20
 */
package xml

// Use Grape to handle the downloading of required jars, nice!
@Grab(group='xalan', module='xalan', version='2.7.0')
import org.apache.xpath.XPathAPI
import groovy.xml.DOMBuilder 

def home = System.properties['user.home']

def customers = DOMBuilder.parse(new FileReader("$home/dev/play/groovy/xml/customers.xml")).documentElement
XPathAPI.selectNodeList(customers, '//customer').each { customer -> 
    def company = XPathAPI.eval(customer, '@company')
    def value = XPathAPI.eval(customer, 'sum(purchase/@price)').num()
    printf "%s %s total=%f%n", XPathAPI.eval(customer, '@name').str(), (company) ? company.str() : "", value
    
}