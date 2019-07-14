package com.headstorm.slackbots.bots

import com.headstorm.slackbots.clients.MTMClient
import com.headstorm.slackbots.config._

/**
  * Bot for getting and creating MTM consultant locations
  */
class MTMBot[F[_]](mtmClient: MTMClient[F]) extends Bot {

  lazy val token: Token = config.slackbots.mtm.token
  lazy val channel: String = config.slackbots.mtm.channelId

  client.onMessage { message =>
    val userName: String = state.getUserById(message.user).map(_.name).getOrElse("unknown user")

    mtmClient.getConsultantLocations
    mtmClient.setConsultantLocation(userName)

  }

}
