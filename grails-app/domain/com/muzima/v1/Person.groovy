package com.muzima.v1

import com.muzima.AppUser
import grails.rest.Resource

@Resource(formats = ['json', 'xml'])
class Person {

    String gender
    Date birthdate

    AppUser creator
    Date dateCreated

    AppUser changedBy
    Date dateChanged

    static hasMany = [personNames: PersonName, personAddresses: PersonAddress]

    static constraints = {
        gender nullable: false, blank: false
        birthdate nullable: false, max: new Date()

        creator nullable: false
        dateCreated nullable: false

        changedBy nullable: true
        dateChanged nullable: true
    }

    String getGivenName() {
        if (!personNames) {
            personNames.each {
                if (it.preferred) {
                    it.givenName
                }
            }
            personNames.each {
                if (!it.voided) {
                    it.givenName
                }
            }
        }
        return null
    }

    String getMiddleName() {
        if (!personNames) {
            personNames.each {
                if (it.preferred) {
                    it.middleName
                }
            }
            personNames.each {
                if (!it.voided) {
                    it.middleName
                }
            }
        }
        return null
    }

    String getFamilyName() {
        if (!personNames) {
            personNames.each {
                if (it.preferred) {
                    it.middleName
                }
            }
            personNames.each {
                if (!it.voided) {
                    it.middleName
                }
            }
        }
        return null
    }
}
