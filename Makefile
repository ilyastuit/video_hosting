up: create-war npm-install docker-build docker-up migrations

down: docker-down

create-war:
	./gradlew cleanWar war --stacktrace

clean-build:
	./gradlew clean build --stacktrace

unit-tests:
	./gradlew unitTest --rerun-tasks --stacktrace

integration-tests:
	./gradlew integrationTest --rerun-tasks

npm-install:
	#cd frontend; npm install

migrations:
	./gradlew update --stacktrace

docker-build:
	docker-compose build

docker-up:
	docker-compose up -d

docker-down:
	docker-compose down --remove-orphans

watch-frontend:
	docker-compose exec frontend-nodejs npm run watch

build-backend:
	docker-compose build backend-tomcat

restart-backend:
	make create-war
	docker-compose restart backend-tomcat

build-db-dev:
	docker-compose build postgres-dev

restart-db-dev:
	docker-compose restart postgres-dev

build-db-test:
	docker-compose build postgres-test

restart-db-test:
	docker-compose restart postgres-test

build-frontend:
	docker-compose build frontend-nginx
	docker-compose build frontend-nodejs

restart-frontend:
	docker-compose restart frontend-nginx
	docker-compose restart frontend-nodejs