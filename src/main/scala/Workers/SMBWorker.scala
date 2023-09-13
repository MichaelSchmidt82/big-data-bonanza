package Workers

import Filetypes.Archive
import com.hierynomus.smbj.SMBClient
import com.hierynomus.smbj.auth.AuthenticationContext
import com.hierynomus.smbj.share.DiskShare

import scala.collection.convert.ImplicitConversions.`iterable AsScalaIterable`

object SMBWorker {

  private val auth = new AuthenticationContext("foo", "bar".toCharArray, "home.lan")
  private val client = new SMBClient()

  private val session = client
    .connect("utility-box.home.lan")
    .authenticate(auth)

  // Connect to Share
  private val share: DiskShare = session.connectShare("foo_share").asInstanceOf[DiskShare]

  private val files = share
    .list("Documents", "*")
    .filter {
      _.getFileName matches """^(?!.*\.\_|.DS_Store|^\.{1,2}$).*$"""
    }

  for (f <- files) {
    println(f.getFileName)
  }

  def upload(compressedFile: Archive):
}
