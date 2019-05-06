package com.example.alison.autprime;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class theNote implements Serializable {
    private String nTitle;
    private String nContent;
    private long nDateTime;

    public theNote(String title, String content, long created) {
        this.nTitle = title;
        this.nContent = content;
        this.nDateTime = created;
    }

    public String getNTitle() {
        return nTitle;
    }

    public void setNTitle(String title) {
        this.nTitle = title;
    }

    public String getNContent() {
        return nContent;
    }

    public void setNContent(String content) {
        this.nContent = content;
    }

    public long getNDateTime() {
        return nDateTime;
    }

    public void formatNDateTime(long date_time) {
        this.nDateTime = date_time;
    }

    public String formatNDateTime(Context context) {
        SimpleDateFormat created = new SimpleDateFormat("dd/MM/yyyy HH/mm"
                , context.getResources().getConfiguration().locale);
        created.setTimeZone(TimeZone.getDefault());
        return created.format(nDateTime);
    }

}
