#!/usr/bin/env groovy
/**
 * Ant builder for the sample Groovylets
 *
 * @author Warwick Hunter
 * @since  2011-06-15
 */

// Add the ant jar to the classpath at runtime
groovyHome = System.getenv('GROOVY_HOME')
new File("$groovyHome/lib").eachFileMatch(~/ant.*\.jar/, { 
    this.class.classLoader.rootLoader.addURL(new URL("file:$it"))
})

cwd = System.properties['user.dir']
if (cwd.endsWith("groovy")) {
    BASE_DIR  = "groovlets"
} else {
    BASE_DIR  = "."
}
BUILD_DIR  = "$BASE_DIR/build"
SRC_DIR    = "$BASE_DIR/."
LIB_DIR    = "$BASE_DIR/lib"
DEPLOY_DIR = new File(System.getenv("CATALINA_HOME") + "/webapps")
if (!DEPLOY_DIR.canWrite()) {
    println "CATALINA_HOME/webapps is not accessible"
    return
}

def groovletBuilder = new AntBuilder()
groovletBuilder.delete(dir:BUILD_DIR)
groovletBuilder.mkdir(dir:BUILD_DIR)
groovletBuilder.sequential {
    war destfile:"$BUILD_DIR/mygroovlets.war", webxml:"$SRC_DIR/web.xml", {
        fileset dir:SRC_DIR, {
            include name:"*.groovy"
            exclude name:"Ant*.groovy"
        }
        lib dir:LIB_DIR
    }
    copy(file:"$BUILD_DIR/mygroovlets.war", todir:"$DEPLOY_DIR")
}
