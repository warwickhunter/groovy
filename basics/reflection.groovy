/**
 * Experiments with reflection
 * 
 * @author Warwick Hunter
 * @since  2011-06-19
 */
class Wasa {
    public static final String COL_A = "a";
    public static final String COL_B = "b";

    public void dump() {
        for (java.lang.reflect.Field field : getClass().getFields()) {
            printf "%s = %s %n", field.getName(), field.get(this).toString()
        }
    }
}

def w = new Wasa();
w.dump()




