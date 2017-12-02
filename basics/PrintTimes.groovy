/** 
 * Print in a human readable form some UNIX times.
 */
ArrayList<Long> times = [1511884389L,1511874638L,1511896613L,1511870978L]

Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
times.each { time ->
    Date d = new Date(time * 1000)
    cal.setTime(d)
    printf '%1$tF %1$tT %1$tZ %n', cal
}

return 0