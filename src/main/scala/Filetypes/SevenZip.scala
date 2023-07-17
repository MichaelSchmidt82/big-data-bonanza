package Filetypes

import io.netty.buffer.ByteBuf
import org.apache.commons.compress.archivers.sevenz.SevenZFile
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream
import org.apache.commons.compress.utils.SeekableInMemoryByteChannel

import java.io.{File, FileInputStream, FileOutputStream}
import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class SevenZip(val path: String) extends App {

  def decompress(): List[File] = {

    val sevenZFile = new File(path)
    val decompressedFiles = ListBuffer.empty[File]

    val sevenFileInputStream = new FileInputStream(sevenZFile)

    @tailrec
    def extractEntry() = {
      val entry = sevenZFile.getNextEntry
      val content = new Array[Byte](entry.getSize)

      val outputFile = new FileOutputStream(outputFilePath)

      @tailrec
      def writeBytes(content: Array[Byte], bytesRead: Int): Unit = {
        if (bytesRead != -1) {
          outputFile.write(content, 0, bytesRead)
          writeBytes(content, sevenFileInputStream.read())
        }
      }
    }


  }

}
