#!/usr/bin/env groovy
/**
 * Groovy SQL experiments
 * 
 * @author Warwick Hunter
 * @since  2011-03-22
 */
import groovy.sql.Sql

// Add the jdbc jar to the classpath at runtime
this.class.classLoader.rootLoader.addURL(new URL("file:/usr/share/java/postgresql-jdbc.jar"))

try {
    displayProject = { project ->
        println "Project $project.name"
        if (project.base)        println "    base        : $project.base"
        if (project.created)     println "    created     : $project.created"
        if (project.description) println "    description : $project.description"
        if (project.state)       println "    state       : $project.state"
        if (project.url)         println "    url         : $project.url"
        println ''
    }
    stmt = Sql.newInstance("jdbc:postgresql://localhost/si", "si", null, "org.postgresql.Driver")
    stmt.eachRow('select * from "PROJECT"', displayProject)
} catch (Exception e) {
    println e.message
}

try {
    displayUser = { user ->
        println "$user.username"
        println "    $user.first_name $user.last_name"
        println "    $user.email"
    }
    stmt = Sql.newInstance("jdbc:postgresql://localhost/reviewboard", "reviewboard", null, "org.postgresql.Driver")
    stmt.eachRow('select * from auth_user', displayUser)
} catch (Exception e) {
    println e.message
}
