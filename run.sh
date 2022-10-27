#!/bin/bash

export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/data-fetching-dev
export SPRING_JPA_HIBERNATE_DDL_AUTO=update

mvn spring-boot:run
