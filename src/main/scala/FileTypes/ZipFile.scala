package FileTypes

import java.io.File

class ZipFile(val path: String) extends Archive {

    def decompress(): Seq[File] = {


      val someFile: File = new File(path)

      println("decompress")

      val payload: Seq[File] = Seq(someFile)
      payload
    }
}
