up: create-jar npm-install docker-build docker-up

down: docker-down

create-jar:
	./gradlew clean bootJar --stacktrace --info

npm-install:
	cd frontend; npm install

docker-build:
	docker-compose build

docker-up:
	docker-compose up -d

docker-down:
	docker-compose down --remove-orphans

watch-frontend:
	docker-compose exec frontend-nodejs npm run watch

build-backend:
	docker-compose build backend

restart-backend:
	docker-compose restart backend

build-frontend:
	docker-compose build frontend-nginx
	docker-compose build frontend-nodejs

restart-frontend:
	docker-compose restart frontend-nginx
	docker-compose restart frontend-nodejs