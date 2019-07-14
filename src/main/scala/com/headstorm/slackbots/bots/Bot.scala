package com.headstorm.slackbots.bots

import akka.actor.ActorSystem
import slack.rtm.{RtmState, SlackRtmClient}

import scala.concurrent.ExecutionContextExecutor

trait Bot {

  def token: Token

  implicit val system: ActorSystem = ActorSystem("slack")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  protected lazy val client: SlackRtmClient = SlackRtmClient(token)
  protected lazy val state: RtmState = client.state
  protected lazy val selfId: String = state.self.id

}
