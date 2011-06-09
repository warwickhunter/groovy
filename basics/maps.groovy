/**
 * Just some messing around with maps
 */
class Result {
    String name;
    Integer value;
    Result(String aName, Integer aValue) {
        name = aName;
        value = aValue;
    }
}
map = new HashMap<String, Result>();
map.put("a", new Result("b", 0));
map.put("c", new Result("d", 1));

for (Map.Entry<String, Result> entry : map.entrySet()) {
    printf "%s=%s/%d%n", entry.getKey(), entry.getValue().name, entry.getValue().value
    entry.getValue().value++
}

for (Map.Entry<String, Result> entry : map.entrySet()) {
    printf "%s=%s/%d%n", entry.getKey(), entry.getValue().name, entry.getValue().value
}

