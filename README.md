# Pivotal Cloud Cache Look-aside Demo

Welcome to the PCCDemo Application. This demo repository is solely focused on simple examples of a look-aside cache implementation.
The structure of this repo is that there are multiple branches that represent different look-aside implementations. 

e.g 
* Look-aside using GemFire API 
* look-aside using [Spring Data Geode](https://github.com/spring-projects/spring-data-geode) 
* look-aside using [Spring Boot Data Geode](https://github.com/spring-projects/spring-boot-data-geode)

All of these examples have been tested against [Pivotal Cloud Cache](https://pivotal.io/pivotal-cloud-cache).

These examples are merely simple starting points to prove out complexity or simplicity of each approach.
These examples are NOT tutorials on each product used.

For [Pivotal](https://pivotal.io/) employees all of these examples were tested against PCFOne (available on the Okta site).

For non-Pivotal employees, these examples will run against any [Pivotal Cloud Cache](https://pivotal.io/pivotal-cloud-cache) 1.7.0 environment that you might have access to.

What this demo wants to show is the progression of a simple app from [Pivotal GemFire](https://pivotal.io/pivotal-gemfire) to using [Spring Data Geode](https://github.com/spring-projects/spring-data-geode) to eventually using
[Spring Boot Data Geode](https://github.com/spring-projects/spring-boot-data-geode). (follow branches `using-GemFireAPI` -> `using-SDG` -> `using-SBDG`).

Of the main themes is the natural progression from an application that requires intimate knowledge of its infrastructure to the same application that does not directly expose that knowledge, or requires the user to have that knowledge,
as the  [Spring Boot Data Geode](https://github.com/spring-projects/spring-boot-data-geode) and [Spring Boot](https://spring.io/guides/gs/spring-boot/) removes that required knowledge and a developer can focus on what they are good at,
writing code.

## Common Setup

All examples require a working [Pivotal Cloud Cache](https://pivotal.io/pivotal-cloud-cache) 1.7.0 installation. In addition each example requires a [Pivotal Cloud Cache](https://pivotal.io/pivotal-cloud-cache) service instance, by default the service name  `pccService` is assumed.

To create a service instance using the `cf-cli` use the command, after having logged in and targeted the correct space, 

`cf cs p-cloudcache extra-small pccService`