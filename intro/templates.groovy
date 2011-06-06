#!/usr/bin/env groovy
/*
 * Template experiments inspired by the Groovy in Action book
 *
 * @author Warwick Hunter
 * @since  2011-06-06
 */

def templateString = 'Hello $name <%=age%> (<% for (i in 1..age) { printf "%d ",  i } %>)'
def engine = new groovy.text.SimpleTemplateEngine()
def template = engine.createTemplate(templateString)
def bindings = [[ name:"Wasa", age:46], [name:"Jack", age:5], [name:"Scruffy", age:5]]
for (b in bindings) {
    println template.make(b)
}
 