package Filetypes

import io.netty.buffer.ByteBuf
import org.apache.commons.compress.archivers.sevenz.{SevenZArchiveEntry, SevenZFile}
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel

import java.io.{File, FileInputStream, FileOutputStream}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class SevenZip(val path: String) extends App {

  def decompress(): List[File] = {
    val sevenZFile = new SevenZFile(new File(path))
    val decompressedFiles = ListBuffer.empty[File]

    @tailrec
    def extractEntry(entry: SevenZArchiveEntry): Unit = {
      if (entry != null) {

        val outputFilePath = new File(entry.getName)
        val outputFile = new FileOutputStream(outputFilePath)

        @tailrec
        def writeBytes(content: Array[Byte], bytesRead: Int): Unit = {
          if (bytesRead != -1) {
            outputFile.write(content, 0, bytesRead)
            writeBytes(content, sevenZFile.read())
          }
        }

        val content = new Array[Byte](4096)

        writeBytes(content, sevenZFile.read(content))
        outputFile.close()
        decompressedFiles += outputFilePath
      }
      extractEntry(sevenZFile.getNextEntry)
    }

    extractEntry(sevenZFile.getNextEntry)

    // close files

    decompressedFiles.toList
  }
}
