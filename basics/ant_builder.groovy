#!/usr/bin/env groovy
/**
 * Ant builder example from "Groovy in Action"
 *
 * @author Warwick Hunter
 * @since  2011-04-10
 */

// Add the ant jar to the classpath at runtime
groovyHome = System.getenv('GROOVY_HOME')
new File("$groovyHome/lib").eachFileMatch(~/ant.*\.jar/, { 
    this.class.classLoader.rootLoader.addURL(new URL("file:$it"))
})

BUILD_DIR = "../build"
SRC_DIR = "."

def builder = new AntBuilder()
builder.delete(dir:BUILD_DIR)
builder.mkdir(dir:BUILD_DIR)
builder.sequential {
    taskdef name:"groovyc", classname:"org.codehaus.groovy.ant.Groovyc"
    groovyc srcdir:SRC_DIR, destdir:BUILD_DIR, {
        javac source:"1.6", target:"1.6", debug:"on"
    }
    jar destfile:"$BUILD_DIR/mygroovy.jar", {
        fileset dir:BUILD_DIR, {
            include name:"*.class"
        }
    }
}