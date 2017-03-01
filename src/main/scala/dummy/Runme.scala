package dummy

import java.io.{File, FileInputStream}
import java.nio.file.Path

import org.jruby.embed.ScriptingContainer
import org.jruby.{Ruby, RubyClass}

object Runme {

  lazy val railsRoot: Path = new File(".").toPath.resolve("examples/dummy")
  lazy val applicationFile: Path = railsRoot resolve "config/application.rb"
  lazy val bundlerDir: Path = railsRoot resolve "vendor/bundler/bundler-1.14.5/lib"
  lazy val vendoredGemsDir: Path = railsRoot resolve "vendor/bundle"

  import scala.collection.JavaConversions._

  lazy val sc: ScriptingContainer = new ScriptingContainer

  lazy val ruby: Ruby = {
    val runtime = sc.getProvider.getRuntime
    runtime.setCurrentDirectory(railsRoot)

    val loadPaths : List[String] = List(pathToString(bundlerDir)) ++ sc.getLoadPaths
    sc.setLoadPaths(loadPaths)
    sc.runScriptlet(
      s"""
         | p File.exist?('./vendor/bundler/gems/bundler-1.14.5/lib/bundler')
         | p ENV["GEM_HOME"]
         | require_relative './vendor/bundler/gems/bundler-1.14.5/lib/bundler'
         |
       """.stripMargin)

    runtime
  }

  def main(args: Array[String]): Unit = {
    val inputStream = new FileInputStream(applicationFile.toFile)
    ruby.compileAndLoadFile(applicationFile, inputStream, false)
    val user: RubyClass = ruby.getClass("User")
    println(user)
  }

  private

  implicit def pathToString(path: Path): String = path.toFile.getCanonicalPath

}
