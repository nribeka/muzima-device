package com.muzima

import grails.rest.Resource

@Resource(formats = ['json', 'xml'])
class Authority {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
