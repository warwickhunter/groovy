#!/usr/bin/env groovy
/**
 * Experiment with the SQL features of Groovy. This corresponds to chapter 10 of "Groovy in Action"
 * 
 * @author Warwick Hunter
 * @since  2011-03-22
 */
package sql;

// JDBC drivers can be loaded this way with Grape - Alas Eclipse doesn't co-operate with Grape/grab
//@GrabConfig(systemClassLoader=true)
//@Grab(group='postgresql', module='postgresql', version='[8,)')
import groovy.sql.Sql

import java.sql.Date

// Or the JDBC drivers can be loaded this way
this.class.classLoader.rootLoader.addURL(new URL("file:/usr/share/java/postgresql-jdbc.jar"))

// Use SQL
println "------------------ Using SQL -------------------------"
def db = null
try {
    db = Sql.newInstance("jdbc:postgresql://localhost/groovy", "whunter", null, "org.postgresql.Driver")
    tables = db.connection.metaData.getTables(null, "public", "athlete", null)
    tableExists = tables.next()
    if (tableExists) {
        db.execute('drop table athlete')
    }
    db.execute '''
        create table athlete (
            firstname   varchar(64),
            lastname    varchar(64),
            dateOfBirth date,
            created     timestamp default 'now');
        '''
    String insert = "insert into athlete (firstname, lastname, dateOfBirth) values (?, ?, ?);"
    db.execute insert, ['Kathy', 'Freeman',   Date.valueOf('1970-01-01')]
    db.execute insert, ['Lance', 'Armstrong', Date.valueOf('1968-01-01')]
    db.execute insert, ['Cadell','Evans',     Date.valueOf('1972-01-01')]
    displayAthlete = { athlete ->
        println "$athlete.firstname $athlete.lastname, $athlete.dateofbirth, $athlete.created"
    }
    db.eachRow('select * from athlete', displayAthlete)

} catch (Exception e) {
    println e.message
}

// Use DataSet
println "------------------ Using DataSet -------------------------"
try {
    athleteSet = db.dataSet('Athlete')
    athleteSet.add(firstname:'Will', lastname:'Genia', dateOfBirth:Date.valueOf('1980-01-01'))    
    athleteSet.each { athlete -> 
        println "$athlete.firstname $athlete.lastname, $athlete.dateofbirth, $athlete.created"
    }
    rugbyPlayers = athleteSet.findAll { it.firstname == 'Will' }
    rugbyPlayers.each { athlete ->
        println "Rugby: $athlete.firstname $athlete.lastname, $athlete.dateofbirth, $athlete.created"
    }
} catch (Exception e) {
    println e.message
} finally {
    if (db) db.close()
}
