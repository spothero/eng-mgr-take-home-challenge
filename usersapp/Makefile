-include ../.env.$(or $(APP_ENV),dev)
export

APP_NAME ?= usersapp

mkfile_path := $(abspath $(lastword $(MAKEFILE_LIST)))
current_dir := $(notdir $(patsubst %/,%,$(dir $(mkfile_path))))

.PHONY: repl
repl: ## Run a REPL for development
	poetry run python

.PHONY: run
run: ## Run app locally
	poetry run scraper 4

.PHONY: test
test: ## Runs the tests
	poetry run pytest

.PHONY: lint
lint: ## Lints the app
	poetry run black . --check
	poetry run mypy scraper
	poetry run autoflake --remove-all-unused-imports \
		 --remove-duplicate-keys --remove-unused-variables \
		 scraper/**/*.py test/**/*.py

.PHONY: fmt
fmt: ## Formats the code
	poetry run black .
	poetry run autoflake --in-place --remove-all-unused-imports \
		 --remove-duplicate-keys --remove-unused-variables \
		 scraper/**/*.py test/**/*.py

.PHONY: db.make-migration
db.make-migration: ## Auto-generate migration, provide MSG
	poetry run alembic revision --autogenerate -m "$(MSG)"

.PHONY: db.migrate
db.migrate: ## Run migration
	poetry run alembic upgrade head

.PHONY: db.reset
db.reset: ## Resets the DB
	docker-compose down
	docker volume rm scraper_postgres_data
	docker-compose up -d db
	sleep 1 # Need to sleep before we can run db.migrate
	$(MAKE) db.migrate
	$(MAKE) db.seed
	$(MAKE) docker.dump-schema

.PHONY: db.seed
db.seed: ## Seeds the DB
	poetry run seed_db

.PHONY: db.prompt
db.prompt: ## Fires up the DB prompt
	@PGHOST=$(POSTGRES_HOST) PGUSER=$(POSTGRES_USER) PGDATABASE=$(POSTGRES_DB) PGPASSWORD=$(POSTGRES_PASSWORD) psql

#################
# Docker targets
#################

.PHONY: docker.db-prompt
docker.db-prompt: ## Jumps into the Postgres DB psql prompt
	docker-compose exec db psql -U $(POSTGRES_USER) -d $(POSTGRES_DB)

help: ## Prints this help message
	@grep -h -E '^[a-zA-Z0-9\._-]+:.*?## .*$$' $(MAKEFILE_LIST) |\
		sort | \
		awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'
.DEFAULT_GOAL := help
