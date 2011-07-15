/**
 * Experiment with the Groovy classes that allow Groovy to be embedded in applications.
 * 
 * @authow Warwick Hunter
 * @since 2011-0716
 */
package embed

// Use GroovyShell.evaluate to do instant script execution
binding = new Binding()
binding.x = 42
binding.y = 20

shell = new GroovyShell(binding)
area = "x * y"
assert shell.evaluate(area) == 840
println shell.evaluate(area)

binding.setVariable("x", 1)
assert shell.evaluate(area) == 20
println shell.evaluate(area)

// Use GroovyShell.parse to generate a reusable script
script = shell.parse(area)
script.binding.x = 10
script.binding.y = 12
assert script.run() == 120
println script.run()




