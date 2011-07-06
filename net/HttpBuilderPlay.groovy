#!/usr/bin/env groovy
/**
 * Mess around with HTTPBuilder to see what I can do with it.
 * 
 * @author Warwick Hunter
 * @since 2011-07-06
 */
package net

// Use Grape to handle the downloading of required jars, nice!
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.1' )

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

def http = new HTTPBuilder('http://www.boxofficemojo.com')

http.request( GET, TEXT ) { req ->
  uri.path = '/franchises'
  headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

  // response handler for a success response code:
  response.success = { resp, reader ->
    reader.each { line ->
        if (line =~ "/movies/" && line =~ 'align="center"') {
            pos1 = line.indexOf("href=")
            if (pos1 >= 0) {
                pos2 = line.indexOf('">', pos1)
                if (pos2 >= 0) {
                    pos3 = line.indexOf('</a>', pos2)
                    printf "%s,", line.substring(pos1 + 6, pos2)
                    if (pos3 >= 0) {
                        printf "%s%n", line.substring(pos2 + 2, pos3)
                    }
                }
            }
        }            
    }
  }

  response.failure = { resp ->
    println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
  }
}

return 0
