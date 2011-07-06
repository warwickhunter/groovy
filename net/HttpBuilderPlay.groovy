#!/usr/bin/env groovy
package net

// Use Grape to handle the downloading of required jars, nice!
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.1' )

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

/**
 * Mess around with HTTPBuilder to see what I can do with it.
 * 
 * @author Warwick Hunter
 * @since 2011-07-06
 */

def http = new groovyx.net.http.HTTPBuilder('http://ajax.googleapis.com')

// perform a GET request, expecting JSON response data
http.request( GET, JSON ) {
  uri.path = '/ajax/services/search/web'
  uri.query = [ v:'1.0', q: 'Calvin and Hobbes' ]

  headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'

  // response handler for a success response code:
  response.success = { resp, json ->
    println resp.statusLine

    // parse the JSON response object:
    json.responseData.results.each {
      println "  ${it.titleNoFormatting} : ${it.visibleUrl}"
    }
  }

  // handler for any failure status code:
  response.failure = { resp ->
    println "Unexpected error: ${resp.statusLine.statusCode} : ${resp.statusLine.reasonPhrase}"
  }
}