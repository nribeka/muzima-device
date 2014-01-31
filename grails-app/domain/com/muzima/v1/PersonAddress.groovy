package com.muzima.v1

import com.muzima.AppUser
import grails.rest.Resource

@Resource
class PersonAddress {
    boolean preferred = false

    String address1
    String address2
    String address3
    String address4
    String address5
    String address6

    String cityVillage
    String countyDistrict
    String stateProvince
    String postalCode
    String country

    String latitude
    String longitude

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
        address1 nullable: false, blank: false
        address2 nullable: true
        address3 nullable: true
        address4 nullable: true
        address5 nullable: true
        address6 nullable: true

        cityVillage nullable: true
        countyDistrict nullable: true
        stateProvince nullable: true
        postalCode nullable: true
        country nullable: true

        latitude nullable: true
        longitude nullable: true

        creator nullable: false
        dateCreated nullable: false

        changedBy nullable: true
        dateChanged nullable: true

        voidedBy nullable: true
        dateVoided nullable: true
        voidReason nullable: true
    }
}
