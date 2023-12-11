#!/bin/bash

set -e
set -u

function create_user_and_database() {
        local database=$1
        local user=$2
        local defaultdb=$3
        echo "  Creating user and database '$database'"
        psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" -d $defaultdb <<-EOSQL
            CREATE DATABASE "$database";
            GRANT ALL PRIVILEGES ON DATABASE "$database" TO $user;
EOSQL
}

if [ -n "$POSTGRES_MULTIPLE_DATABASES" ]; then
        echo "Multiple database creation requested: $POSTGRES_MULTIPLE_DATABASES"
        for db in $(echo $POSTGRES_MULTIPLE_DATABASES | tr ',' ' '); do
                create_user_and_database $db $POSTGRES_USER $POSTGRES_DB
        done
        echo "Multiple databases created"
fi