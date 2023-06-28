package FileTypes

import org.apache.commons.compress.compressors.bzip2.{BZip2CompressorInputStream, BZip2CompressorOutputStream}

import java.io._

class Bzip2File(val path: String) extends Archive {

  def decompress(): Seq[File] = {

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

object Bzip2File {

  // Default to BZip2 Format
  def compress(files: Seq[File], outputPath: String): File = {

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

  def compress(zipFile: ZipFile): File = {
    val files: Seq[File] = zipFile.decompress()
    this.compress(files, zipFile.path)
  }

  def compress(sevenZip: SevenZip): File = {
    new File("foo")
  }
}
