# Getting Started

## How to start

First thing you need to do is run the docker containers.

run the following command in the root directory of the project:

```
docker compose -v up -d
```

to stop the docker containers do
```
docker compose down
```

You might have deleted the pre-existing volumes if you are encountering issues with DB.


## Flyway Usage

Under resources -> db.migration create the migration files with the following naming convention:

```V<version_number>__<description>.sql
```