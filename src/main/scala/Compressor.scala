import org.apache.commons.compress.compressors.CompressorStreamFactory
import java.io.{BufferedOutputStream, FileOutputStream}

class Compressor(val Filename: String, val Data: Array[Byte]) {

  try {
    val FileStream: FileOutputStream = new FileOutputStream(Filename)
    val BufferedStream: BufferedOutputStream = new BufferedOutputStream(FileStream)

    val Bzip2Compressor = new CompressorStreamFactory()
      .createCompressorOutputStream(CompressorStreamFactory.BZIP2, BufferedStream)

    Bzip2Compressor.write(Data)

    BufferedStream.close()
    Bzip2Compressor.close()
  }
}
