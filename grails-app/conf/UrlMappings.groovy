class UrlMappings {

	static mappings = {
        "/person"(resources: "person") {
            "/personName"(resources: "personName")
            "/personAddress"(resources: "personAddress")
        }
	}
}
