package com.headstorm.slackbots.config

final case class Config(slackbots: SlackBots, clients: Clients, server: Server)

final case class SlackBots(
  mtm: MTMBotConfig,
  standup: StandupBotConfig,
  chess: ChessBotConfig,
  harvest: HarvestBotConfig,
  scalaRepl: ScalaBotConfig,
  onitamago: OnitamagoBotConfig,
  testing: TestingConfig
                          )
final case class Clients(mtm: MTMApi, lichess: LichessApi, harvest: HarvestApi, onitamago: OnitamagoApi)
final case class Server(host: String, port: Int)

final case class MTMApi(api: String, clientId: String, clientSecret: String)
final case class LichessApi(api: String, token: String)
final case class HarvestApi(api: String, token: String)
final case class OnitamagoApi(api: String, token: String)

final case class MTMBotConfig(token: String, channelId: String)
final case class StandupBotConfig(token: String, channelId: String)
final case class ChessBotConfig(token: String, channelId: String)
final case class HarvestBotConfig(token: String, channelId: String)
final case class OnitamagoBotConfig(token: String, channelId: String)
final case class ScalaBotConfig(token: String, channelId: String)
final case class TestingConfig(token: String, channelId: String)