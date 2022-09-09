# Patient Samurai

Simple application for managing patients. See CHANGELOG.md file to get details about development process

Features are:
- Display patient list
    - Search by patient
    - Filter by patient
- Create patient
    - Add validation
- Update patient
- Delete patient

## Stack

- Clojure
- ClojureScript
- PostgreSQL

## Editor

Sublime Text 4 with installed Clojure (Sublimed) package

## Installation

Add `PATIENT_SAMURAI_DB_URL` environment variable to connect to PostgreSQL server

```
jdbc:postgresql://localhost:5432/patient_samurai?user=USERNAME&password=PASSWORD
```

### Database migrations

Create migration file
```
sh scripts/create-migration.sh NAME
```

Apply migrations
```
sh scripts/apply-migrations.sh
```

### Development

Run in 1st terminal tab:
```
clj -M:dev-client
```

Run in 2nd terminal tab:
```
clj -M:dev-repl
```

### Production

```
sh scripts/package.sh
```

## Tests

```
sh scripts/test.sh
```

## Deployment

I will add CI using Github Actions and CD using Kubernetes on ... FIXME
