package com.headstorm.slackbots.bots

import com.headstorm.slackbots.config._

/**
  * Bot for getting and creating MTM consultant locations
  */
class MTMBot[F[_]] extends Bot {

  lazy val token: Token = config.slackbots.mtm.token
  lazy val channel: String = config.slackbots.mtm.channelId

  //  client.onMessage { message =>
//    message
//  }

}
