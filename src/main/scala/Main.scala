import Filetypes.ZipFile
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

  val p: String = "foo"
  val z: ZipFile = new ZipFile(p)
  val b: BZipTwoFile = new BZipTwoFile(z.path)
  val f: File = b.compress()
}