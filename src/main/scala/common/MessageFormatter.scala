package common

trait MessageFormatter {
  val EOL: String = "\\n"
  def mention(u:String): String = s"<@$u>"
}
