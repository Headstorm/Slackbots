package com.headstorm.slackbots.config

final case class Config(slackbots: SlackBots, clients: Clients, server: Server)

final case class SlackBots(mtm: MTMBotConfig, standup: StandupBotConfig, testing: TestingConfig, chess: ChessBotConfig)
final case class Clients(mtm: MTMApi, lichess: LichessApi)
final case class Server(host: String, port: Int)

final case class MTMApi(api: String, clientId: String, clientSecret: String)
final case class LichessApi(api: String, token: String)

final case class MTMBotConfig(token: String, channelId: String)
final case class StandupBotConfig(token: String, channelId: String)
final case class ChessBotConfig(token: String, channelId: String)
final case class TestingConfig(token: String, channelId: String)