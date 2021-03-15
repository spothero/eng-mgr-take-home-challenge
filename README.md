# SpotHero Engineering Manager Take-Home Challenge

[![App Build](https://github.com/spothero/eng-mgr-take-home-challenge/actions/workflows/app-build.yaml/badge.svg)](https://github.com/spothero/eng-mgr-take-home-challenge/actions/workflows/app-build.yaml)

This repo is a starter project for engineering manager candidates. It runs Postgres 13 in docker, has a database schema with two tables and seed data for both.

## Installation

You need Docker to run this project. Please run `docker-compose up -d` to download the image and start the container, it will create the database, the tables and will add the seed data.
We added a [Makefile](/Makefile) to the project for easier interaction. Run `make help` to see what commands are available for you.
In case the `make` tool is not available on your operating system, open up the Makefile and copy/paste the commands from there.

An easy way to look into the content of the database is running `docker-compose exec db psql -U $(POSTGRES_USER) -d $(POSTGRES_DB)` (or executing the corresponding make command, `make docker.db-prompt`), that will provide the `psql` prompt, the command-line tool of PostgreSQL.

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
* Dockerizing the API project and adding it to `docker-compose`.
