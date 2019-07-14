package com.headstorm.slackbots.bots

import com.headstorm.slackbots.clients.LichessClient
import com.headstorm.slackbots.config.config

/**
  * Bot to create games and send links to users using the Lichess API
  */
class ChessBot[F[_]](lichessClient: LichessClient[F]) extends Bot {

  lazy val token: Token = config.slackbots.chess.token
  lazy val channel: String = config.slackbots.chess.channelId

  client.onMessage { message =>
    val userName: String = state.getUserById(message.user).map(_.name).getOrElse("unknown user")

    lichessClient.challengeUser(message.text).right.map{ challenge =>
      val sendToChannel: String = s"$userName challenges ${message.text} to a chess duel!  https://lichess.org/${challenge.challenge.id}"

      client.sendMessage(channel, sendToChannel)
    }

  }

}