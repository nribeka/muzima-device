import com.muzima.v1.Person
import com.muzima.v1.PersonAddress
import com.muzima.v1.PersonName
import grails.rest.render.json.JsonRenderer

// Place your Spring DSL code here
beans = {

    personAddressRenderer(JsonRenderer, PersonAddress) {
        excludes = ['class', 'person']
    }

    personNameRenderer(JsonRenderer, PersonName) {
        excludes = ['class', 'person']
    }

    personRenderer(JsonRenderer, Person) {
        excludes = ['class', 'users']
    }
}
