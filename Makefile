up: create-jar docker-build docker-up

down: docker-down

create-jar:
	./gradlew clean bootJar --stacktrace --info

docker-build:
	docker-compose build

docker-up:
	docker-compose up -d

docker-down:
	docker-compose down --remove-orphans