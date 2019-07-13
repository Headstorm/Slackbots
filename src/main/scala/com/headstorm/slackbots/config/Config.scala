package com.headstorm.slackbots.config

final case class Mtm(api: String, clientId: String, clientSecret: String)

final case class Slack(token: String)

final case class Server(host: String, port: Int)

final case class Config(slack: Slack, mtm: Mtm, server: Server)
