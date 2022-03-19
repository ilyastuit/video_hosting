up: create-jar docker-up

down: docker-down

create-jar:
	./gradlew clean bootJar --stacktrace --info

docker-up:
	docker-compose up -d

docker-down:
	docker-compose down --remove-orphans