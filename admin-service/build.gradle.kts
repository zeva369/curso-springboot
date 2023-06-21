plugins {
	java
	id("org.springframework.boot") version "2.7.12"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.seva.cursospringboot"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

extra["springBootAdminVersion"] = "2.7.4"

dependencies {
	implementation("de.codecentric:spring-boot-admin-starter-server")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("de.codecentric:spring-boot-admin-dependencies:${property("springBootAdminVersion")}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
