# Charter app

## 1. Run application for local development

### Start back-end (`server`)

Linux
```bash
cd server
gradle bootRun 
```
Windows:
```bash
cd server
 ./gradlew bootRun
```

- By default, back-end use port 8033
- Java 17
- SpringBoot 2.7.5

### Start front-end (`client`)

```bash
cd client/battleship
npm install
npm start
```
- React 18 with redux 
- Front-end is currently prepared to test back-end.
