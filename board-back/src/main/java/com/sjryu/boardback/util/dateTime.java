package com.sjryu.boardback.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public  class dateTime {

    public static String simpleDateTime() {

    Date now = Date.from(Instant.now());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String writeDatetime = simpleDateFormat.format(now);

    return writeDatetime;
    }
}
