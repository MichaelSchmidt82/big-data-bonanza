import org.apache.spark.{SparkConf, SparkContext}

import java.io.File

object Main extends App {
  val conf = new SparkConf().setAppName("SparkVersion").setMaster("local[*]")
  val sc = new SparkContext(conf)
  val sparkVersion = sc.version
  println(s"Spark Version: $sparkVersion")
  sc.stop()
}

object Foo extends App {

  val path: String = "some_path"

  val zurpFile: ZipFile = new ZipFile(path)
  val fyle: File = new File(path)

  val bar = Bzip2File.apply(zurpFile)
  val baz = Bzip2File.apply(fyle)
}