import org.apache.commons.compress.compressors.bzip2.{BZip2CompressorInputStream, BZip2CompressorOutputStream}
import java.io.{BufferedInputStream, File, FileInputStream, FileOutputStream, InputStream}

trait Archive {

  // Default to BZip2 Format
  def compress(files: Seq[File], outputPath: String): Unit = {

    val outputFile = new File(outputPath)
    val outputStream = new FileOutputStream(outputFile)
    val bzip2OutputStream = new BZip2CompressorOutputStream(outputStream)

    try {
      for (file <- files) {
        val inputStream = new FileInputStream(file)
        val buffer = new Array[Byte](4096)
        var bytesRead = inputStream.read(buffer)

        while (bytesRead != -1) {
          bzip2OutputStream.write(buffer, 0, bytesRead)
          bytesRead = inputStream.read(buffer)
        }

        inputStream.close()
      }
    } finally {
      bzip2OutputStream.finish()
      bzip2OutputStream.close()
      outputStream.close()
    }
  }

  def decompress(compressedFile: File, outputPath: String): Unit
  def integrationTest(): Unit = {}
}


class BZip2File extends App with Archive {

  def decompress(compressedFile: File, outputPath: String): Unit = {

    // Input Streams
    val fileIn: InputStream = new FileInputStream(compressedFile)
    val buffedFileIn: BufferedInputStream = new BufferedInputStream(fileIn)
    val bzip2InputStream = new BZip2CompressorInputStream(buffedFileIn)

    val buffer = new Array[Byte](4096)
    var entryName = 1
    var bytesRead = 0

    while ( {
      bytesRead = bzip2InputStream.read(buffer)
      bytesRead
    } != -1) {
      val outputFile = new File(outputPath, s"decompressed_$entryName")
      val outputStream = new FileOutputStream(outputFile)
      outputStream.write(buffer, 0, bytesRead)
      outputStream.close()

      entryName += 1
    }

    bzip2InputStream.close()
  }

  val f: File = new File("some_bz2_for_you.bz2")
  decompress(f, "path/")
}

class ZipFile extends Archive {

  override def decompress(compressedFile: File, outputPath: String): Unit = ???

}