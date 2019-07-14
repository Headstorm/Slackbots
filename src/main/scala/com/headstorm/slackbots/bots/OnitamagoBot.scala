package com.headstorm.slackbots.bots

import com.headstorm.slackbots.clients.OnitamagoClient
import com.headstorm.slackbots.config.config

/**
  * Bot to interact with the Onitamago API to manage performance reviews via Slack
  */
class OnitamagoBot[F[_]](onitamagoClient: OnitamagoClient[F]) extends Bot {

  lazy val token: Token = config.slackbots.onitamago.token

  client.onMessage { message =>

    val client = onitamagoClient
    client.formatted(message.text)
  }
}