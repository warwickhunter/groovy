#!/usr/bin/env groovy
// An experiment with calendar string formatting
import java.util.Calendar;
c = Calendar.getInstance();
printf "%tF %tT %tz %n", c, c, c
