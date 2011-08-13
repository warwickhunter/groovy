#!/usr/bin/env groovy
/**
 * Experiment with the Groovy classes that allow Groovy to parse XML in a streaming manner
 * 
 * @author Warwick Hunter
 * @since 2011-08-13
 */
package xml

import groovy.xml.StreamingMarkupBuilder

def home = System.properties['user.home']

def customers = new XmlSlurper().parse(new File("$home/dev/play/groovy/xml/customers.xml"))
def builder = new StreamingMarkupBuilder()
def copier = builder.bind{ mkp.yield(customers) }
println copier


customers = new XmlSlurper().parse(new File("$home/dev/play/groovy/xml/customers.xml"))

Closure markup = {
    html {
        head {
            style(type:"text/css", '''  
                .tab {
                     border:1px solid #4b3900;  
                     margin-top: 10px; 
                     vertical-align:top;
                     border-collapse:collapse; 
                }
               .fixed {  
                   width: 200px;
                   vertical-align:top; 
                   padding:3px 10px 3px 10px; 
                   margin:0px 0px 5px 0px; 
                }  
            ''')  
            title('Customers of ACME Technology')
        }
        body {
            h1('Customers of ACME Technology')
            table('class':'tab') {
                thead {
                    tr('align':'center') {
                        th('colspan':'2', 'Corporate customers')
                    }
                }
                customers.corporate.customer.each{ customer ->
                    tr {
                        td('class':'fixed', "${customer.@name}")
                        td('class':'fixed', "${customer.@company}")
                    }
                }
                thead {
                    tr('align':'center') {
                        th('colspan':'2', 'Personal customers')
                    }
                }
                customers.consumer.customer.each{ customer ->
                    tr {
                        td('class':'fixed', "${customer.@name}")
                        td('class':'fixed', "")
                    }
                }
            }
        }
    }
}

def processor = new groovy.xml.StreamingMarkupBuilder().bind(markup)
new File("$home/dev/play/groovy/xml/customers.html").withWriter{ it << processor }
Runtime.runtime.exec "google-chrome $home/dev/play/groovy/xml/customers.html"

