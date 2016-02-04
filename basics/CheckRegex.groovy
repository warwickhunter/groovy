def s = ["NCIS: Los Angeles foo", "NCIS Los Angeles"]

def expr = "NCIS:? Los Angeles.*"

s.each {
    printf "%s -> %s %s%n", it, expr, it.matches(expr)
}
return 0