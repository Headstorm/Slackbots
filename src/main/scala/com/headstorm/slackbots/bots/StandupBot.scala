package com.headstorm.slackbots.bots

import com.headstorm.slackbots.config._
import scribe._

/**
  * Async standup Bot
  */
class StandupBot[F[_]] extends Bot {

  lazy val token: Token = config.slackbots.standup.token
  lazy val channel: String = config.slackbots.standup.channelId
  if (channel.equalsIgnoreCase("D010EU2MKEY")) {
    client.onMessage { message =>
      this.logger.info(s"Message received $message")
      val statusTitle: Iterator[String] = Iterator.apply("Feeling", "Yesterday", "Today", "Blocked")
      val userName: String = state.getUserById(message.user).map(_.name).getOrElse("unknown user")
      this.logger.info(s"Channel is $channel")
      val sendToChannel: String = message.text
        .split("\\|")
        .toList
        .map(i => s"*${statusTitle.next}*\n>$i")
        .mkString(s"*Standup for: ${userName}*\n\n", "\n", "")

      client.sendMessage(channel, sendToChannel.mkString)
    }
  }
}
