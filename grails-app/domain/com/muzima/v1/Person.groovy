package com.muzima.v1

import com.muzima.AppUser
import grails.rest.Resource

@Resource
class Person {

    String gender
    Date birthdate

    AppUser creator
    Date dateCreated

    AppUser changedBy
    Date dateChanged

    static hasMany = [personName: PersonName, personAddress: PersonAddress]

    static constraints = {
        gender nullable: false, blank: false
        birthdate nullable: false, max: new Date()

        creator nullable: false
        dateCreated nullable: false

        changedBy nullable: true
        dateChanged nullable: true
    }
}
