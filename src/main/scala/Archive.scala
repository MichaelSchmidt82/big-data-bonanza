import java.io.File

trait Archive {

  def decompress(): List[File]

  def integrationTest(): Unit = {}
}
