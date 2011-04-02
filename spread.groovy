/**
 * Spread example from "Groovy in Action"
 * 
 * @author Warwick Hunter
 * @since  2011-04-03
 */

def getList() {
   return [1, 2, 3]
}
def sum(a, b, c) {
   return a + b + c
}
assert 6 == sum(*list)
assert 6 == sum(*[1,2,3])

def range = (1..3)
assert [0, 1, 2, 3] == [0, *range]
assert [0, 1, 2, 3] == [0, *(1..3)]

def map = [a:1, b:2]
assert [a:1, b:2, c:3] == [c:3, *:map]
assert [a:1, b:2, c:3] == [c:3, *:[a:1, b:2]]



