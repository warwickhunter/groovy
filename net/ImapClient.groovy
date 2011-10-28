#!/usr/bin/env groovy
/**
 * Read all the email messages from an Imap email account.
 *
 * @author Warwick Hunter
 * @since  2011-10-28
 */
@Grapes ([
    @Grab (group = 'javax.activation', module = 'activation', version = '1.1'),
    @Grab (group = 'javax.mail', module = 'mail', version = '1.4')
])

import groovy.xml.StreamingMarkupBuilder
import javax.activation.*
import javax.mail.*
import javax.mail.internet.*
import javax.mail.search.*
import java.util.Properties

class MailContentProcessor {
   def build(builder, String content) {
       builder.string(content)
   }
  
   def build(builder, Multipart mp) {
       builder.multipart {
           (1..mp.getCount()).each { n ->
               def p = mp.getBodyPart(n - 1)
               part {
                   contentType(p.getContentType())
                   def a
                   def d = p.getDisposition()
                   if (d) {
                       disposition(d)
                       a = d.toLowerCase().equals(BodyPart.ATTACHMENT)
                   }
                   if (a) {
                       fileName(p.getFileName())
                       size(p.getSize())
                       // Add code to save attachment if desired...
                   } else {
                       build(builder, p.getContent())
                   }
               }
           }
       }
   }

   def build(builder, Message m) {
       builder.message(id:m.getHeader("Message-ID")[0]) {
           sender(m.sender)
           subject(m.subject)
           contentType(m.getContentType())
           build(builder, m.getContent())
       }
   }
} // class

class ImapSslClient {
   def hostname
   def username
   def password
  
   def getStore = {
       // configure the jvm to use the jsse security.
       java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
       
       Properties props = new Properties([
                                   "mail.imap.socketFactory.class": "javax.net.ssl.SSLSocketFactory",
                                   "mail.imap.socketFactory.fallback": "false",
                                   "mail.imap.socketFactory.port": "993"
                                       ]
                                     )

       def cons = System.console()
       while (!password) {
           def m = "password for ${username} on ${hostname}: "
           password = cons.readPassword(m).toString()
       }
       def name = new URLName("imap://${username}:${password}@${hostname}")
//       Session.getInstance(props).getStore(name)
       Session.getInstance(new Properties()).getStore(name)
   }
  
   def processMessages(callBack, callBackParam) {
       def inbox;
       def store;
       
       try {
           store = getStore()
           print "3."
           store.connect()
           print "2."
           inbox = store.getFolder("social")
           print "1."
           inbox.open(Folder.READ_ONLY)
           def terms = new FlagTerm(new Flags(Flags.Flag.DELETED), false)
           def l = inbox.search(terms)
           print "0."
           l.each() { m -> callBack(m, callBackParam) }
       } finally {
           if(inbox) {
               inbox.close(false) // do not expunge
           }
           if (store)   {
               store.close()
           }
           println "Done"
       }
   }
} // class

if (args.length < 3) {
   println "Usage: mailtoxml <hostname> <username> <output.xml>"
   System.exit(1)
}

def proc = new MailContentProcessor()

def myMessageCallback = { m, b ->
     print "."
     proc.build(b, m)
}

def builder = new StreamingMarkupBuilder()

new FileOutputStream(args[2]) << builder.bind { b ->
   messageList {
       new ImapSslClient(hostname: args[0],
                         username: args[1]).processMessages(myMessageCallback, b)
   }
}

// Teppo Peltonen 2010
