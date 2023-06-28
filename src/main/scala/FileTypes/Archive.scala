package FileTypes

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream
import java.io.{File, FileInputStream, FileOutputStream}


trait Archive { def decompress(): Seq[File]

  def integrationTest(): Unit = {}
}
