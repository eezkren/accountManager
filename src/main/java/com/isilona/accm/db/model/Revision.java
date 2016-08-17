package com.isilona.accm.db.model;

import javax.persistence.Entity;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity
@RevisionEntity(CustomRevisionListener.class)
public class Revision extends DefaultRevisionEntity {

    private static final long serialVersionUID = -3501059059811802486L;

    private String remoteIp;

    public String getRemoteIp() {
	return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
	this.remoteIp = remoteIp;
    }

}
