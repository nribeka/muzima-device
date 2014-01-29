import com.muzima.Authority
import com.muzima.WebUser
import com.muzima.WebUserAuthority

class BootStrap {

    def init = { servletContext ->

        def devAuthority = new Authority(authority: 'ROLE_DEVELOPER').save(flush: true)
        def adminAuthority = new Authority(authority: 'ROLE_ADMINISTRATOR').save(flush: true)
        def userAuthority = new Authority(authority: 'ROLE_USER').save(flush: true)

        def devWebUser = new WebUser(username: 'developer', password: 'developer').save(flush: true)
        def adminWebUser = new WebUser(username: 'admin', password: 'password').save(flush: true)
        def userWebUser = new WebUser(username: 'user', password: 'password').save(flush: true)

        WebUserAuthority.create(devWebUser, devAuthority, true)
        WebUserAuthority.create(adminWebUser, adminAuthority, true)
        WebUserAuthority.create(userWebUser, userAuthority, true)

        assert WebUser.count() == 3
        assert Authority.count() == 3
        assert WebUserAuthority.count() == 3
    }
    def destroy = {
    }
}
