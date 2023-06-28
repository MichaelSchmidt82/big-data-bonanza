import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream

import java.io.{BufferedInputStream, File, FileInputStream, FileOutputStream, InputStream}

class Bzip2File(path: String) extends Archive {

  def decompress(path: String): Unit = {

    // Input Streams
    val file: File = new File(path)
    val fileIn: InputStream = new FileInputStream(file)
    val buffedFileIn: BufferedInputStream = new BufferedInputStream(fileIn)
    val bzip2InputStream = new BZip2CompressorInputStream(buffedFileIn)

    val buffer = new Array[Byte](4096)
    var entryName = 1
    var bytesRead = 0

    while ( {
      bytesRead = bzip2InputStream.read(buffer)
      bytesRead
    } != -1) {
      val outputFile = new File(path, s"decompressed_$entryName")
      val outputStream = new FileOutputStream(outputFile)
      outputStream.write(buffer, 0, bytesRead)
      outputStream.close()

      entryName += 1
    }

    bzip2InputStream.close()
  }
}
