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

}
