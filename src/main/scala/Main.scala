import org.apache.spark.{SparkConf, SparkContext}

object Main extends App {
  val conf = new SparkConf().setAppName("SparkVersion").setMaster("local[*]")
  val sc = new SparkContext(conf)
  val sparkVersion = sc.version
  println(s"Spark Version: $sparkVersion")
  sc.stop()
}

