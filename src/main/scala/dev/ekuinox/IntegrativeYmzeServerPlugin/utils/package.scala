package dev.ekuinox.IntegrativeYmzeServerPlugin

package object utils {

  /**
   * 配列から.でチェーンしたパスを生成する
   */
  def makePath(arr: Array[String]): String = arr.mkString(".")

}
