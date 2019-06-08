#!/bin/sh
docker-compose -f docker-compose.yaml -f docker-compose.app.yaml up --build --exit-code-from sor_producer
