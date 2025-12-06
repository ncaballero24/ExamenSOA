# Examen Replica - Different (Deliverable)
This project is a complete reimplementation (different codebase) that replicates the high-level functionality:
- REST API (CRUD) for students
- SOAP endpoint (getStudentRequest) at /ws
- Frontend (React) to list and add students
- Flyway migration for initial data
- Dockerfiles + docker-compose to run locally

To build locally without Docker:
- Backend: mvn package && java -jar backend/target/examen-replica-0.0.1-SNAPSHOT.jar
- Frontend: cd frontend && npm install && npm start (or npm run build)

To run with Docker Compose:
docker compose up --build

Ports:
- Backend: 8080
- Frontend: 3000
