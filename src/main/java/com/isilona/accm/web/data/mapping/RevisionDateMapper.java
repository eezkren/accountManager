package com.isilona.accm.web.data.mapping;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class RevisionDateMapper {

    public String toRevisionDate(long timestamp) {
	Date date = new Date(timestamp);
	Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
	return format.format(date);
    }

}
