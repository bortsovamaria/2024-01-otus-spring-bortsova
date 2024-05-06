Before run:

    mvn clean install

    Run docker (port 5030): 
    docker run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=library -p 5430:5432 postgres:13


For backend tests run docker-test container ((port 5028):

    docker run --rm --name pg-docker-test -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=test_library -p 5428:5432 postgres:13

Hint: ./docker