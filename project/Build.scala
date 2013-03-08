import sbt._

import Keys._
import AndroidKeys._

object General {
  val settings = Defaults.defaultSettings ++ Seq(
    name := "Scalculator",
    version := "0.1",
    versionCode := 0,
    scalaVersion := "2.9.2",
    libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test")

  val proguardSettings = Seq(
    useProguard in Android := true)

  lazy val fullAndroidSettings =
    General.settings ++
      AndroidProject.androidSettings ++
      TypedResources.settings ++
      proguardSettings ++
      AndroidManifestGenerator.settings ++
      AndroidMarketPublish.settings ++ Seq(
        platformName in Android := "android-8",
        keyalias in Android := "change-me")
}

object AndroidBuild extends Build {
  lazy val main = Project(
    "Scalculator",
    file("."),
    settings = General.fullAndroidSettings) dependsOn calcLogic

  lazy val calcLogic = Project(
    "CalcLogic",
    file("calc-logic"),
    settings = General.settings
      ++ Seq(
        name := "CalcLogic"))
}
