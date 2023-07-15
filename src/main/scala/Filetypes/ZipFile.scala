package Filetypes

import org.apache.commons.compress.archivers.zip.{ZipArchiveEntry, ZipArchiveInputStream}

import java.io.{File, FileInputStream, FileOutputStream}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class ZipFile(val path: String) extends Archive {

  def decompress(): List[File] = {
    val zipFile = new File(path)
    val decompressedFiles = ListBuffer.empty[File]

    val zipFileInputStream = new FileInputStream(zipFile)
    val zipInputStream = new ZipArchiveInputStream(zipFileInputStream)

    @tailrec
    def extractEntry(entry: ZipArchiveEntry): Unit = {
      if (entry != null) {
        val outputFilePath = new File(entry.getName)

        if (entry.isDirectory) {
          outputFilePath.mkdirs()
        } else {
          val outputFile = new FileOutputStream(outputFilePath)

          @tailrec
          def writeBytes(buffer: Array[Byte], bytesRead: Int): Unit = {
            if (bytesRead != -1) {
              outputFile.write(buffer, 0, bytesRead)
              writeBytes(buffer, zipInputStream.read(buffer))
            }
          }

          val buffer = new Array[Byte](4096)
          writeBytes(buffer, zipInputStream.read(buffer))
          outputFile.close()
          decompressedFiles += outputFilePath
        }

        extractEntry(zipInputStream.getNextZipEntry)
      }
    }

    extractEntry(zipInputStream.getNextZipEntry)

    zipInputStream.close()
    zipFileInputStream.close()

    decompressedFiles.toList
  }

}
