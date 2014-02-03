package com.muzima

import com.muzima.v1.Person
import grails.rest.Resource

@Resource(formats = ['json', 'xml'])
class AppUser {

	transient springSecurityService

    Person person
	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired



	static transients = ['springSecurityService']

	static constraints = {
        person nullable: true, validator: { value, object ->
            if (!object.username.equalsIgnoreCase("root") && object.person == null) {
                return false;
            }
        }
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

    String getGivenName() {
        if (!person) {
            println 'getting given name for someone who is not null'
            person.getGivenName()
        }
        null
    }

    String getMiddleName() {
        if (!person) {
            println 'getting middle name for someone who is not null'
            person.getMiddleName()
        }
        null
    }

    String getFamilyName() {
        if (!person) {
            println 'getting family name for someone who is not null'
            person.getFamilyName()
        }
        null
    }

	Set<Authority> getAuthorities() {
		AppUserAuthority.findAllByAppUser(this).collect { it.authority } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
