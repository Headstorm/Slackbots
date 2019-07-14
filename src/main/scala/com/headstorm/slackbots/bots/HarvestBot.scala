package com.headstorm.slackbots.bots

import com.headstorm.slackbots.clients.HarvestClient
import com.headstorm.slackbots.config.config

/**
  * Bot to interact with the Harvest API to manage time tracking and costs
  */
class HarvestBot[F[_]](harvestClient: HarvestClient[F]) extends Bot {

  lazy val token: Token = config.slackbots.harvest.token

  client.onMessage { message =>

    val client = harvestClient
    client.formatted(message.text)
  }

}