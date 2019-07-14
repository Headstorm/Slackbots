package com.headstorm.slackbots.bots

import com.headstorm.slackbots.config._

/**
  * Async standup Bot
  */
class StandupBot[F[_]] extends Bot {

  lazy val token: Token = config.slackbots.standup.token
  lazy val channel: String = config.slackbots.standup.channelId

  client.onMessage { message =>
    val statusTitle: Iterator[String] = Iterator.apply("Feeling", "Yesterday", "Today", "Blocked")
    val userName: String = state.getUserById(message.user).map(_.name).getOrElse("unknown user")
    val sendToChannel: String = message.text
      .split("\\|")
      .toList
      .map(i => s"*${statusTitle.next}*\n>$i")
      .mkString(s"*Standup for: ${userName}*\n\n", "\n", "")

    client.sendMessage(channel, sendToChannel.mkString)
  }
}
