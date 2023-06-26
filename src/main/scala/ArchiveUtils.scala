import org.apache.commons.compress.compressors.CompressorStreamFactory
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream

import java.io.{BufferedInputStream, BufferedOutputStream, File, FileInputStream, FileOutputStream, InputStream}

class Compressor(val OutPath: String, val Filename: String, val Data: Array[Byte]) {

  try {
    val FileStream: FileOutputStream = new FileOutputStream(s"$OutPath/$Filename")
    val BufferedStream: BufferedOutputStream = new BufferedOutputStream(FileStream)

    val Bzip2Compressor = new CompressorStreamFactory()
      .createCompressorOutputStream(CompressorStreamFactory.BZIP2, BufferedStream)

    Bzip2Compressor.write(Data)

    BufferedStream.close()
    Bzip2Compressor.close()
  }
}

object Decompressor extends App {

  val fi: File = new File("some_file.bz2")
  val inStream: InputStream = new FileInputStream(fi)
  val buffed: BufferedInputStream = new BufferedInputStream(inStream)
  val input: Array[Byte] = new BZip2CompressorInputStream(buffed).readAllBytes()

  println(input)
}
