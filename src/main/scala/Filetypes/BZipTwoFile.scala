import Filetypes.{Archive, ZipFile}
import org.apache.commons.compress.archivers.tar.{TarArchiveEntry, TarArchiveInputStream}
import org.apache.commons.compress.compressors.bzip2.{BZip2CompressorInputStream, BZip2CompressorOutputStream}

import java.io._
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class BZipTwoFile(val bziptwoFilePath: String) extends Archive {

  def decompress(): List[File] = {
    val bZipTwoFile = new File(bziptwoFilePath)
    val decompressedFiles = ListBuffer.empty[File]

    val bZipTwoFileInputStream = new FileInputStream(bZipTwoFile)

    val bZipTwoInputStream = new BZip2CompressorInputStream(bZipTwoFileInputStream)
    val tarInputStream = new TarArchiveInputStream(bZipTwoInputStream)

    @tailrec
    def decompressEntry(entry: Option[TarArchiveEntry], files: List[File]): List[File] = entry match {
      case Some(currentEntry) =>
        val outputFilePath = new File(currentEntry.getName)
        val outputFile = new FileOutputStream(outputFilePath)

        @tailrec
        def writeBytes(buffer: Array[Byte], bytesRead: Int): Unit = {
          if (bytesRead != -1) {
            outputFile.write(buffer, 0, bytesRead)
            writeBytes(buffer, tarInputStream.read(buffer))
          }
        }

        val buffer = new Array[Byte](4096)
        writeBytes(buffer, tarInputStream.read(buffer))
        outputFile.close()

        decompressEntry(Option(tarInputStream.getNextTarEntry), outputFilePath :: files)

      case None =>
        tarInputStream.close()
        bZipTwoInputStream.close()
        files
    }

    decompressEntry(Option(tarInputStream.getNextTarEntry), Nil).reverse

    decompressedFiles.toList
  }

  // Default to BZip2 Format
  def compress(files: List[File], outputPath: String): File = {
    val outputFile = new File(outputPath)
    val outputStream = new FileOutputStream(outputFile)
    val bzip2OutputStream = new BZip2CompressorOutputStream(outputStream)

    @tailrec
    def compressFiles(fileList: List[File]): Unit = fileList match {
      case Nil =>
        // All files compressed, close streams
        bzip2OutputStream.finish()
        bzip2OutputStream.close()
        outputStream.close()
      case file :: remainingFiles =>
        val inputStream = new FileInputStream(file)
        val buffer = new Array[Byte](4096)
        var bytesRead = inputStream.read(buffer)

        @tailrec
        def writeBytes(buffer: Array[Byte], bytesRead: Int): Unit = {
          if (bytesRead != -1) {
            bzip2OutputStream.write(buffer, 0, bytesRead)
            writeBytes(buffer, inputStream.read(buffer))
          }
        }

        writeBytes(buffer, bytesRead)
        inputStream.close()

        compressFiles(remainingFiles)
    }

    compressFiles(files.toList)
    outputFile
  }

  def compress(path: String): File = {
    new File(path)
  }

  def compress(): File = {
    new File(bziptwoFilePath)
  }

  def compress(zipFile: ZipFile): File = {
    val files: List[File] = zipFile.decompress()
    this.compress(files, zipFile.path)
  }

  //def compress(sevenZip: SevenZip): File = {
  //new File("foo")
}

object BZipTwoFile {

  def apply(zipFile: ZipFile): BZipTwoFile = new BZipTwoFile(zipFile.path)

  def apply(file: File): BZipTwoFile = new BZipTwoFile(file.getPath)
}
