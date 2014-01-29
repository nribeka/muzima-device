package com.muzima

import org.apache.commons.lang.builder.HashCodeBuilder

class WebUserAuthority implements Serializable {

	private static final long serialVersionUID = 1

	WebUser webUser
	Authority authority

	boolean equals(other) {
		if (!(other instanceof WebUserAuthority)) {
			return false
		}

		other.webUser?.id == webUser?.id &&
			other.authority?.id == authority?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (webUser) builder.append(webUser.id)
		if (authority) builder.append(authority.id)
		builder.toHashCode()
	}

	static WebUserAuthority get(long webUserId, long authorityId) {
		WebUserAuthority.where {
			webUser == WebUser.load(webUserId) &&
			authority == Authority.load(authorityId)
		}.get()
	}

	static WebUserAuthority create(WebUser webUser, Authority authority, boolean flush = false) {
		new WebUserAuthority(webUser: webUser, authority: authority).save(flush: flush, insert: true)
	}

	static boolean remove(WebUser u, Authority r, boolean flush = false) {

		int rowCount = WebUserAuthority.where {
			webUser == WebUser.load(u.id) &&
			authority == Authority.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(WebUser u) {
		WebUserAuthority.where {
			webUser == WebUser.load(u.id)
		}.deleteAll()
	}

	static void removeAll(Authority r) {
		WebUserAuthority.where {
			authority == Authority.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['authority', 'webUser']
		version false
	}
}
