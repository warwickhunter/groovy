/**
 * Example of CompileStatic from the talk "Groovy: The Awesome Parts" by Paul King 
 * 
 * https://yow.eventer.com/yow-2014-1222/groovy-the-awesome-parts-by-paul-king-1700
 */
import groovy.transform.builder.Builder
import groovy.transform.builder.InitializerStrategy
import groovy.transform.Immutable
import groovy.transform.CompileStatic

@Immutable
@Builder(builderStrategy=InitializerStrategy)
class Person {
    String first
    String last
    int age
}

@CompileStatic
def main() {
    println new Person(Person.createInitializer().first("John").last("Smith").age(21))
}

main()