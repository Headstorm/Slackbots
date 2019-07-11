package config

sealed trait Slackbots

final case class MTM(clientId: String, clientSecret: String) extends Slackbots

final case class Slack(token: String) extends Slackbots