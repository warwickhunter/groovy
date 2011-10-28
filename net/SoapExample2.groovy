#!/usr/bin/env groovy
/**
 * Groovy sample from "Groovy in Action" book. Using SOAP to explore web services.
 * This time using a different SOAP library.
 * 
 * @author Warwick Hunter
 * @since  2011-08-27
 */

@Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='0.3')
import wslite.soap.*

class TemperatureClient {

    SOAPClient client = new SOAPClient("http://www.w3schools.com/webservices/tempconvert.asmx")
    
    /** Invoke the SOAP CelsiusToFahrenheit method */
    String toFahrenheit(String celsius) {
        try {
            def response = client.send(SOAPAction: "http://tempuri.org/CelsiusToFahrenheit") {
                version SOAPVersion.V1_1        // SOAPVersion.V1_1 is default
                soapNamespacePrefix "SOAP"      // "soap-env" is default
                encoding "UTF-8"                // "UTF-8" is default encoding for xml
                body {
                    CelsiusToFahrenheit(xmlns:"tns:http://tempuri.org/") {
                        Celsius(celsius)
                    }
                }
            }
            if (response.hasFault()) {
                println response.getFault()
                return null
            } else {
                println "response $response"
                println "headers ${response.headers}"
                println "body ${response.body}"
                return response
            }
        } catch (Exception e) {
            println e.message
            return null
        }
    }
}

def client = new TemperatureClient()
println client.toFahrenheit("0")
println client.toFahrenheit("30")

return 0