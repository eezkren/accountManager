package com.isilona.accm.web.data.response;

/**
 * @author iivanov
 *
 */
public class AccountRevisionDto {

    private AccountDto account;

    private CustomRevisionDto revision;

    public AccountDto getAccount() {
	return account;
    }

    public void setAccount(AccountDto account) {
	this.account = account;
    }

    public CustomRevisionDto getRevision() {
	return revision;
    }

    public void setRevision(CustomRevisionDto revision) {
	this.revision = revision;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((account == null) ? 0 : account.hashCode());
	result = prime * result + ((revision == null) ? 0 : revision.hashCode());
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
	AccountRevisionDto other = (AccountRevisionDto) obj;
	if (account == null) {
	    if (other.account != null)
		return false;
	} else if (!account.equals(other.account))
	    return false;
	if (revision == null) {
	    if (other.revision != null)
		return false;
	} else if (!revision.equals(other.revision))
	    return false;
	return true;
    }

}
