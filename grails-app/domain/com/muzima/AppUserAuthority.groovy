package com.muzima

import org.apache.commons.lang.builder.HashCodeBuilder

class AppUserAuthority implements Serializable {

	private static final long serialVersionUID = 1

	AppUser appUser
	Authority authority

	boolean equals(other) {
		if (!(other instanceof AppUserAuthority)) {
			return false
		}

		other.appUser?.id == appUser?.id &&
			other.authority?.id == authority?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (appUser) builder.append(appUser.id)
		if (authority) builder.append(authority.id)
		builder.toHashCode()
	}

	static AppUserAuthority get(long appUserId, long authorityId) {
		AppUserAuthority.where {
			appUser == AppUser.load(appUserId) &&
			authority == Authority.load(authorityId)
		}.get()
	}

	static AppUserAuthority create(AppUser appUser, Authority authority, boolean flush = false) {
		new AppUserAuthority(appUser: appUser, authority: authority).save(flush: flush, insert: true)
	}

	static boolean remove(AppUser u, Authority r, boolean flush = false) {

		int rowCount = AppUserAuthority.where {
			appUser == AppUser.load(u.id) &&
			authority == Authority.load(r.id)
		}.deleteAll()

		rowCount > 0
	}

	static void removeAll(AppUser u) {
		AppUserAuthority.where {
			appUser == AppUser.load(u.id)
		}.deleteAll()
	}

	static void removeAll(Authority r) {
		AppUserAuthority.where {
			authority == Authority.load(r.id)
		}.deleteAll()
	}

	static mapping = {
		id composite: ['authority', 'appUser']
		version false
	}
}
