package com.isilona.accm.web.data.response;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.isilona.accm.validation.ValidEmail;

/**
 * @author iivanov
 *
 */
public class AccountDto {

    private String id;
    @NotNull(message = "{validation.account.firstName.NotNull}")
    @Size(min = 2, message = "{validation.account.firstName.Size}")
    private String firstName;

    @NotNull(message = "{validation.account.lastName.NotNull}")
    @Size(min = 2, message = "{validation.account.lastName.Size}")
    private String lastName;

    @NotNull(message = "{validation.account.email.NotNull}")
    @ValidEmail(message = "{validation.account.email.ValidEmail}")
    private String email;

    private String dateOfBirth;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getDateOfBirth() {
	return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
	result = prime * result + ((email == null) ? 0 : email.hashCode());
	result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
	AccountDto other = (AccountDto) obj;
	if (dateOfBirth == null) {
	    if (other.dateOfBirth != null)
		return false;
	} else if (!dateOfBirth.equals(other.dateOfBirth))
	    return false;
	if (email == null) {
	    if (other.email != null)
		return false;
	} else if (!email.equals(other.email))
	    return false;
	if (firstName == null) {
	    if (other.firstName != null)
		return false;
	} else if (!firstName.equals(other.firstName))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (lastName == null) {
	    if (other.lastName != null)
		return false;
	} else if (!lastName.equals(other.lastName))
	    return false;
	return true;
    }

}
