package com.headstorm.slackbots.clients

import akka.actor.ActorSystem
import com.headstorm.slackbots.config._
import slack.SlackUtil
import slack.rtm.SlackRtmClient
import scribe._

import scala.concurrent.ExecutionContextExecutor

class SlackClient[F[_]] {

  implicit val system: ActorSystem = ActorSystem("slack")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  val client: SlackRtmClient = SlackRtmClient(config.slack.token)
  val selfId: String = client.state.self.id

  def sendMessageToSlackChannel(slackMessage: String): Unit = {

    client.onMessage { message =>
      val mentionedIds = SlackUtil.extractMentionedIds(message.text)

      if (mentionedIds.contains(selfId)) {
        client
          .sendMessage(message.channel, s"<@${message.user}>: ```$slackMessage```")
          .logger
          .info(s"sending message $slackMessage")
      }
    }

  }

}
