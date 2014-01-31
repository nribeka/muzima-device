package com.muzima

import grails.rest.Resource

@Resource
class Person {

    String gender
    Date birthdate

    static constraints = {
    }
}
