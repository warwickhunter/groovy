#!/usr/bin/env groovy
/**
 * Import each of the split certificates into the keystore
 * 
 * @author Warwick Hunter 
 * @since  2011-03-22
 */
new File('certs').eachFileRecurse { 
    if (!it.directory) {
        println "importing $it"
        Runtime.runtime.exec("keytool -keystore fred.ks -storepass fredpass -importcert -file $it -trustcacerts")
    }
}