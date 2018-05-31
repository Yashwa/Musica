package com.andygeeks.musica.utils;

import java.util.Locale;

import static java.lang.String.format;

public class TimeUtil {
    public static String secondsToTime(String totalSecs) {

        Long totalSec = Long.parseLong(totalSecs);

        Long hours = totalSec / 3600;
        Long minutes = (totalSec % 3600) / 60;
        Long seconds = totalSec % 60;

        return format(Locale.US, "%02d:%02d:%02d", hours, minutes, seconds);

    }
}
