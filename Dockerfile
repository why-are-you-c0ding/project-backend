#토대가 되는 이미지 FROM. 참고로 주석은 #을 사용하며 문장 맨 앞에서부터 시작해야함.
FROM --platform=linux/x86_64 openjdk:11-jre-slim

#docker build 커맨드를 사용할 때 입력받을 수 있는 인자를 선언한다.
#ARG JAR_FILE=build/*.jar
ARG JAR_FILE=build/libs/\*.jar

#이미지에 파일이나 폴더를 추가. ${JAR_FILE} -> (이미지 파일로 복사) app.jar
COPY ${JAR_FILE} app.jar

#컨테이너를 실행할 때 실행할 명령어를 강제 지정한다.
ENTRYPOINT ["java","-jar","app.jar"]
