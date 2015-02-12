#!/usr/bin/env groovy
/**
 * Use gson to pretty print a JSON file.
 * 
 * @author Warwick Hunter
 * @since 2015-02-12
 */

// Use Grape to handle the downloading of required jars, nice!
@Grapes(
    @Grab(group='com.google.code.gson', module='gson', version='2.3.1')
)

import com.google.gson.*

if (args.length < 1) {
    println "usage: JsonPrettyPrint.groovy file"
    return -1
}

JsonParser parser = new JsonParser()
Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping()create()

for (file in args) {
    FileReader reader = new FileReader(file)
    JsonElement jsonElement = parser.parse(reader)
    println gson.toJson(jsonElement)    
}

return 0