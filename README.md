
# 🚀 Mars Rover Image Explorer

Simple full-stack application to fetch images from the NASA Mars Rover API based on dates from a text file.  
The user selects an image from the frontend and downloads it locally.

---

##  Tech Stack

- Backend: Spring Boot (Java 17)
- Frontend: React.js
- API: NASA Mars Rover API
- Testing: JUnit 5, Mockito
- Build Tool: Maven

## 🚀 How to Run the Project (Local)

### 1. Clone the Repo
git clone https://github.com/SrajitRastogi/SrajitRastogiResiTakeHome.git

cd mars-rover-project

### 2. GET NASA API KEY
 2.1 Visit: https://api.nasa.gov/
 
 2.2 Generate your free API key

 In your backend application.properties, 
 Add your api key
 
 nasa.api.key=YOUR_NASA_API_KEY

 ### 3. Run the backend
 cd backend

./mvnw clean install

./mvnw spring-boot:run

**Backend will run at**
http://localhost:8080

**Health Check**
http://localhost:8080/actuator/health

### 4. Run the frontend
cd client

npm install

npm start

**Frontend will run at**
http://localhost:3000



## How it works
1. Backend reads data from
   backend/src/main/resources/dates.txt
2. Fetches images from NASA Mars Rover API for each date
3. Frontend displays all available images for selection.
4. User selects an image to download.
5. Image is downloaded locally to:
   ~/Downloads/mars-images/

## Testing
### Run backend test
cd backend

./mvnw test

### Check code coverage
./mvnw clean verify

open backend/target/site/jacoco/index.html

### Downloaded image location
~/Downloads/mars-images/



