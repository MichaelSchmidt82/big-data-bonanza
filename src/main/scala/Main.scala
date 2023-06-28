import FileTypes.{Bzip2File, ZipFile}
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
  val some_path: String = "some path"
  val z: ZipFile = new ZipFile(some_path)
  val f: File = Bzip2File.compress(z)
}