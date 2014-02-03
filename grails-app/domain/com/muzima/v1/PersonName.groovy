package com.muzima.v1

import com.muzima.AppUser
import grails.rest.Resource

@Resource(formats = ['json', 'xml'])
class PersonName {
    boolean preferred = false

    String prefix
    String givenName
    String middleName
    String familyName
    String degree

    AppUser creator
    Date dateCreated

    AppUser changedBy
    Date dateChanged

    boolean voided = false
    AppUser voidedBy
    Date dateVoided
    String voidReason

    static belongsTo = [person: Person]

    static constraints = {
        prefix nullable: true
        givenName nullable: false, blank: false
        middleName nullable: true
        familyName nullable: false, blank: false
        degree nullable: true

        creator nullable: false
        dateCreated nullable: false

        changedBy nullable: true
        dateChanged nullable: true

        voidedBy nullable: true
        dateVoided nullable: true
        voidReason nullable: true
    }
}
