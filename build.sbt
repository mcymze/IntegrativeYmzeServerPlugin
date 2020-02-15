name := "integrative-ymze-server-plugin"

version := "0.1"

scalaVersion := "2.13.1"

resolvers += "spigot-repo" at "https://hub.spigotmc.org/nexus/content/repositories/snapshots/"
resolvers += "bungeecord-repo" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += Resolver.jcenterRepo
resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")
libraryDependencies += "org.spigotmc" % "spigot-api" % "1.15.1-R0.1-SNAPSHOT"

assemblyJarName in assembly := s"IntegrativeYamazoe-$version.jar"