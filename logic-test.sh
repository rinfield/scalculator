#!/usr/bin/env sh

if [ "$ANDROID_SDK_ROOT" = "" ] ; then
  export ANDROID_SDK_ROOT=DUMMY
  echo "set ANDROID_SDK_ROOT to '$ANDROID_SDK_HOME'"
fi
if [ "$SCALA_VERSION" = "" ] ; then
  export SCALA_VERSION=2.9.2
  echo "set SCALA_VERSION to '$SCALA_VERSION'"
fi

sbt ++$SCALA_VERSION "project CalcLogic" test