#!/usr/bin/env sh

env

if [ "$ANDROID_SDK_ROOT" = "" ] ; then
  export ANDROID_SDK_ROOT=DUMMY_FOR_TEST
  echo "set ANDROID_SDK_ROOT to '$ANDROID_SDK_ROOT'"
fi
if [ "$TRAVIS_SCALA_VERSION" = "" ] ; then
  export TRAVIS_SCALA_VERSION=2.9.2
  echo "set TRAVIS_SCALA_VERSION to '$TRAVIS_SCALA_VERSION'"
fi

sbt ++$TRAVIS_SCALA_VERSION "project CalcLogic" test