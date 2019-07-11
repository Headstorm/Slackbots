package config

sealed trait Slackbots

case class MTM(a: String) extends Slackbots

case class Slack(b: Int) extends Slackbots

final case class Port(value: Int) extends AnyVal

case class BotConfig(
                    boolean: Boolean,
                    port: Port,
                    adt: Slackbots,
                    list: List[Double],
                    map: Map[String, String],
                    option: Option[String])