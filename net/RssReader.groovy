#!/usr/bin/env groovy
package net

/**
 * Groovy sample from "Groovy in Action" book. Fetching an RSS feed.
 * 
 * @author Warwick Hunter
 * @since  2011-08-27
 */

def url = "http://news.bbc.co.uk/rss/newsonline_uk_edition/front_page/rss091.xml"

println "Top three news items for today"
def items = new XmlParser().parse(url).channel[0].item
for (item in items[0..2]) {
    println item.title.text()
    println item.link.text()
    println item.description.text()
    println '--------------------------------------------------------------------------'
}
