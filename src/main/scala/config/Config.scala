package config

final case class Mtm(clientId: String, clientSecret: String)

final case class Slack(token: String)

final case class Config(slack: Slack, mtm: Mtm)