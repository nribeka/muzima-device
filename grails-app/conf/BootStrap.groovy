import com.muzima.AppUser
import com.muzima.AppUserAuthority
import com.muzima.Authority
import com.muzima.Requestmap
import com.muzima.v1.Person
import com.muzima.v1.PersonAddress
import com.muzima.v1.PersonName
import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        def adminAuthority = new Authority(authority: 'ROLE_ADMIN')
        adminAuthority.save(flush: true, failOnError: true)

        def adminUser = new AppUser(username: 'root', password: 'password')
        adminUser.save(flush: true, failOnError: true)

        def adminAppUserAuthority = AppUserAuthority.create(adminUser, adminAuthority, true)
        adminAppUserAuthority.save(flush: true, failOnError: true)

        assert Authority.count == 1
        assert AppUser.count == 1
        assert AppUserAuthority.count == 1

        def firstDate = "28/09/2010";
        def firstPersonName = new PersonName(
                givenName: "First", familyName: "Family",
                creator: adminUser, dateCreated: new Date())
        def firstPersonAddress = new PersonAddress(
                address1: "Address First Person",
                creator: adminUser, dateCreated: new Date())
        def firstPerson = new Person(
                gender: "M", birthdate: new Date().parse("dd/MM/yyyy", firstDate),
                creator: adminUser, dateCreated: new Date())
        firstPerson.addToPersonNames(firstPersonName)
        firstPerson.addToPersonAddresses(firstPersonAddress)
        firstPerson.save(flush: true, failOnError: true)

        def secondDate = "29/10/2010"
        def secondPersonName = new PersonName(
                givenName: "Second", familyName: "Family",
                creator: adminUser, dateCreated: new Date())
        def secondPersonAddress = new PersonAddress(
                address1: "Address Second Person",
                creator: adminUser, dateCreated: new Date())
        def secondPerson = new Person(
                gender: "F", birthdate: new Date().parse("dd/MM/yyyy", secondDate),
                creator: adminUser, dateCreated: new Date())
        secondPerson.addToPersonNames(secondPersonName)
        secondPerson.addToPersonAddresses(secondPersonAddress)
        secondPerson.save(flush: true, failOnError: true)

        assert Person.count == 2

        new Requestmap(url: '/person/**', configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/login/**', configAttribute: 'permitAll').save()
        new Requestmap(url: '/logout/**', configAttribute: 'permitAll').save()

        JSON.registerObjectMarshaller(AppUser) {
            def map = [:]
            map['id'] = it.id
            map['username'] = it.username
            map['authorities'] = it.getAuthorities()
            map['person'] = ['givenName': it.getGivenName(), 'middleName': it.getMiddleName(), 'familyName': it.getFamilyName()]
        }
    }
    def destroy = {
    }
}
