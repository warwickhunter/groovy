/**
 * Experiment with the Groovy classes that allow Groovy to be embedded in applications.
 * 
 * @authow Warwick Hunter
 * @since 2011-0716
 */
package embed

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

