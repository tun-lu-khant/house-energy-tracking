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

```
V<version_number>__<description>.sql
```

## Initial Project Setup
Go to - File -> Project Structure -> Modules 
Click '+' button and select the 'Import Module' option. Select the 'backend' folder and click 'OK'. In the next window, select 'Import module from external model' and choose 'Maven'. Click 'Next' and then 'Finish'.