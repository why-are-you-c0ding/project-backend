#!/usr/bin/env bash

source ./profile.sh

REPOSITORY=/home/ubuntu/server

JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."
nohup java -jar -Dspring.profiles.active=$IDLE_PROFILE $JAR_PATH  > $REPOSITORY/nohup.out 2>&1 &
