#!/usr/bin/env groovy
/**
 * Node builder example from "Groovy in Action"
 *
 * @author Warwick Hunter
 * @since  2011-04-10
 */
def builder = new NodeBuilder()
def ulcDate = new Date(107,0,1)
def invoices = builder.invoices{
    invoice(date: ulcDate) {
        item(count: 5) {
            product(name:'ULC', dollar:1499)
        }
        item(count: 1) {
            product(name:'Visual Editor', dollar:499)
        }
    }
    invoice(date: new Date(106,1,2)) {
        item(count: 4) {
            product(name:'Visual Editor', dollar:499)
        }
    }
}

soldAt = invoices.grep{
    it.item.product.any{ it.'@name' == 'ULC' }
}.'@date'
assert soldAt == [ulcDate]

def writer = new StringWriter()
invoices.print(new PrintWriter(writer))
println writer

println invoices.depthFirst()*.name()
println invoices.breadthFirst()*.name()
