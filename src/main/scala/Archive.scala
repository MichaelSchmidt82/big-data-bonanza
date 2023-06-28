import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream
import java.io.{File, FileInputStream, FileOutputStream}

trait Archive {


    // Default to BZip2 Format
  def compressToBzip2(files: Seq[File], outputPath: String): Unit = {

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

  def decompress(): Unit

  def integrationTest(): Unit = {}
}
