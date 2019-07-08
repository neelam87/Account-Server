package com.account.domainobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
    name = "account",
    uniqueConstraints = @UniqueConstraint(name = "uc_firstname", columnNames = {"firstname"})
)
public class AccountDO
{

    @Id
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Firstname can not be null!")
    private String firstname;
    
    private String lastname;

    public AccountDO(){
    	
    }

    public AccountDO(Long id, String firstname, String lastname)
    {
    	this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}


	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}


	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

    

	}
