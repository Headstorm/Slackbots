package com.headstorm.slackbots.bots

import akka.actor.ActorSystem
import com.headstorm.slackbots.config._
import slack.rtm.{RtmState, SlackRtmClient}

import scala.concurrent.ExecutionContextExecutor

trait Bot {

  implicit val system: ActorSystem = ActorSystem("slack")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  protected val client: SlackRtmClient = SlackRtmClient(config.slack.token)
  protected val state: RtmState = client.state
  protected val selfId: String = state.self.id

}
