# SpotHero Engineering Manager Take-Home Challenge

[![App Build](https://github.com/spothero/eng-mgr-take-home-challenge/actions/workflows/app-build.yaml/badge.svg)](https://github.com/spothero/eng-mgr-take-home-challenge/actions/workflows/app-build.yaml)

This repo is a starter project for engineering manager candidates. It runs Postgres 13 in docker, has a database schema with two tables and seed data for both.

## Installation

You need Docker to run this project. Please run `docker-compose up -d` to download the image and start the container, it will create the database, the tables and will add the seed data.
We added a [Makefile](/Makefile) to the project for easier interaction. Run `make help` to see what commands are available for you.
In case the `make` tool is not available on your operating system, open up the Makefile and copy/paste the commands from there.

An easy way to look into the content of the database is by running `docker-compose exec db psql -U $(POSTGRES_USER) -d $(POSTGRES_DB)` (or executing the corresponding make command, `make docker.db-prompt`), that will provide the `psql` prompt, the command-line tool of PostgreSQL.

## Our Goals

We would like to assess your skills in the following areas:

* Docker
* Git
* GitHub
* A programming language - current SpotHero Languages (Python, Kotlin, Swift, GoLang)
* SQL
* REST
* APIs

## Your task

We ask you to create an API project on top of this provided database schema. You can use any language, any framework you're confident with.

The application has to expose two `GET` endpoints and one `POST`.

1. `GET` all active users

`curl http://localhost:3000/v1/users` should return a JSON like this (we only captured the first record, your solution should return 10 records):

```json
[
  {
    "id": 1,
    "first_name": "Radchelle",
    "last_name": "Haggerty",
    "email": "RachelleTHaggerty@rhyta.com"
  },
  ...
]
```

2. `GET` all worked_hours for users

The following curl request `curl http://localhost:3000/v1/users/1/worked_hours` should return 6 records in a format like this:


```json
[
  {
    "id": 1,
    "date": "2021-01-01",
    "hours": "3.9"
  },
  {
    "id": 1,
    "date": "2021-01-04",
    "hours": "4.134"
  },
  ...
]
```

3. `POST` a new worked_hour record

By using the following curl request:

```
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"date": "2021-01-11","hours":5.24}' \
  http://localhost:3000/v1/users/1/worked_hours
```

a new `worked_hour` record is inserted into the database.


## Bonus Points

Although it's not required, if you have time, we would love to see:

* Automated tests for your solution,
* A Dockerfile to run the API project in Docker, maybe expanding our [docker-compose.yml](/docker-compose.yml) with it.


## How do I spin up the App and start?

* A Springboot service, implemented using Java exposes the three APIs mentioned above.
* Maven is needed build the Springboot app and create the Docker image.
* Docker is needed to spin up the application with the database
 
 ```
git clone https://github.com/navreddy23/eng-mgr-take-home-challenge.git

cd {project-base-directory}

mvn clean install

docker-compose up -d 

Go to any of the endpoints mentioned above
 ```
 
 ### More Details ...
 
* `mvn clean install` builds the application, after validating the JSON API contract schemas, creating the data 
transfer objects, running Google checkstyle, PMD, unit tests (junit) and integration tests(@SpringbootTest). 
Finally, it creates a Docker image called `spothero-app` which is used by Docker Compose to spin up 
the application with the database

* `mvn clean package` will run everything that `install` does, but it will skip the integration tests making the build 
faster

* The [API Contracts](/src/main/resources/api_contract_schema_specs) are the foundation of the application. 
They are defined first using JSON schema. The Data Transfer Objects are auto generated to `src/generated` 
as part of each Maven build. Having independent API contract schema and data transfer objects decouples 
the API from the internal implementation, and helps store the API contracts schema in version control 
to maintain a change history

* `http://localhost:8080/swagger-ui/` is the Swagger endpoint, though it deserves some much needed work.
