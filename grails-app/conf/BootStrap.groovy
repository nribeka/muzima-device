import com.muzima.AppUser
import com.muzima.AppUserAuthority
import com.muzima.Authority
import com.muzima.Person
import com.muzima.Requestmap

class BootStrap {

    def init = { servletContext ->
        new Person(gender: "M", birthdate: new Date()).save(flush: true)
        new Person(gender: "F", birthdate: new Date()).save(flush: true)

        assert Person.count == 2

        new Requestmap(url: '/person/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/login/**', configAttribute: 'permitAll').save()

        def adminAuthority = new Authority(authority: 'ROLE_ADMIN').save(flush: true)
        def adminUser = new AppUser(username: 'me', password: 'password').save(flush: true)
        AppUserAuthority.create(adminUser, adminAuthority, true).save(flush: true)

        assert Authority.count == 1
        assert AppUser.count == 1
        assert AppUserAuthority.count == 1
    }
    def destroy = {
    }
}
