package com.isilona.accm.db.model;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CustomRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object genericRevision) {

	String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
		.getRemoteAddr();

	Revision revision = (Revision) genericRevision;
	revision.setRemoteIp(remoteAddress);
    }
}
