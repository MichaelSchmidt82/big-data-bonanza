package Filetypes

import java.io.File

class ZipFile(val path: String) extends Archive {

    def decompress(): List[File] = {


      val someFile: File = new File(path)

      println("decompress")

      val payload: List[File] = List(someFile)
      payload
    }
}
