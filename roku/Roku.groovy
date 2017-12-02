// Use Grape to handle the downloading of required jars, nice!
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.1' )

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

def http = new HTTPBuilder('http://10.0.0.113:8060')

http.request( GET, TEXT ) { req ->
  uri.path = 'query/device-info'

  // response handler for a success response code:
  response.success = { resp, reader ->
    reader.each { line ->
        println line
    }
    resp.headers.each { println it }
  }
  
  response.failure = { resp ->
    println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
  }
}

return 0