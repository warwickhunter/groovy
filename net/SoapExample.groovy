#!/usr/bin/env groovy
/**
 * Groovy sample from "Groovy in Action" book. Using SOAP to explore web services
 * 
 * @author Warwick Hunter
 * @since  2011-08-27
 */

@Grab(group='org.codehaus.groovy.modules', module='groovyws', version='0.5.2')
import groovyx.net.ws.WSClient
import groovy.xml.Namespace

//
// Currency convertor
//
def url = "http://www.webservicex.net/CurrencyConvertor.asmx?WSDL"

def wsdl = new Namespace('http://schemas.xmlsoap.org/wsdl/','wsdl')
def doc  = new XmlParser().parse(url)

println doc[wsdl.portType][wsdl.operation].'@name'

// 
// Temperature 
//
proxy = new WSClient("http://www.w3schools.com/webservices/tempconvert.asmx?WSDL", this.class.classLoader)
proxy.initialize()

def temps = [0, 10, 20, 30]
for (temp in temps) {
    result = proxy.CelsiusToFahrenheit(temp)
    println "Temp $temp C == $result F"
}
