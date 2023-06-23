import org.apache.commons.compress.compressors.CompressorStreamFactory
import java.io.{BufferedOutputStream, FileOutputStream}

class Compressor(val Filename: String, val Data: Array[Byte]) {

  try {
    val FileStream: FileOutputStream = new FileOutputStream(Filename)
    val BufferedStream: BufferedOutputStream = new BufferedOutputStream(FileStream)

    val CompApp = new CompressorStreamFactory()
      .createCompressorOutputStream(CompressorStreamFactory.BZIP2, BufferedStream)

    CompApp.write(Data)

    BufferedStream.close()
    CompApp.close()
  }
}
