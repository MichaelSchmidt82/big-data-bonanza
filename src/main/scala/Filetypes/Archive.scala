package Filetypes

import java.io.File

trait Archive extends File {

  def decompress(): List[File]

  def integrationTest(): Unit = {}
}
