package com.isilona.accm.web.data.response;

/**
 * @author iivanov
 *
 */
public class CustomRevisionDto {

    private String id;

    private String remoteIp;

    private String revisionDate;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getRemoteIp() {
	return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
	this.remoteIp = remoteIp;
    }

    public String getRevisionDate() {
	return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
	this.revisionDate = revisionDate;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((remoteIp == null) ? 0 : remoteIp.hashCode());
	result = prime * result + ((revisionDate == null) ? 0 : revisionDate.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	CustomRevisionDto other = (CustomRevisionDto) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (remoteIp == null) {
	    if (other.remoteIp != null)
		return false;
	} else if (!remoteIp.equals(other.remoteIp))
	    return false;
	if (revisionDate == null) {
	    if (other.revisionDate != null)
		return false;
	} else if (!revisionDate.equals(other.revisionDate))
	    return false;
	return true;
    }

}
