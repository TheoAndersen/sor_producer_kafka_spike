# FROM gradle:5.4-jdk8-alpine AS TEMP_BUILD_IMAGE
# ENV APP_HOME=/usr/app/
# WORKDIR $APP_HOME
# COPY build.gradle settings.gradle gradlew $APP_HOME
# COPY gradle $APP_HOME/gradle
# RUN gradle build || return 0
# COPY . .
# RUN gradle build

# FROM openjdk:8-alpine AS TEMP_BUILD_IMAGE
# ENV APP_HOME=/usr/app/
# WORKDIR $APP_HOME
# COPY gradlew $APP_HOME
# COPY gradle.build gradle $APP_HOME/gradle
# RUN ./gradlew build || return 0
# COPY . .
# RUN ./gradlew build
# FROM gradle:5.4-jdk8-alpine
# ENV APP_HOME=/usr/App
# WORKDIR $APP_HOME
# COPY gradlew $APP_HOME
# COPY build.gradle $APP_HOME
# run gradle dependencies -q
# ADD src src
# RUN gradle build --scan -s
FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
RUN ./gradlew dependencies
COPY src src
RUN ./gradlew build -x test
