name := "integrative-ymze-server-plugin"

version := "0.1"

organization := "dev.ekuinox"

scalaVersion := "2.13.1"

resolvers ++= Seq(
  "spigot-repo" at "https://hub.spigotmc.org/nexus/content/repositories/snapshots/",
  "bungeecord-repo" at "https://oss.sonatype.org/content/repositories/snapshots",
)

libraryDependencies ++= Seq(
  "org.spigotmc" % "spigot-api" % "1.15.2-R0.1-SNAPSHOT"
)

assemblyJarName in assembly := s"IntegrativeYamazoe-${version.value}.jar"
