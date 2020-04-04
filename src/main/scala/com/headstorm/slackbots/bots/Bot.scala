package com.headstorm.slackbots.bots

import akka.actor.ActorSystem
import slack.rtm.{RtmState, SlackRtmClient}

import scala.concurrent.ExecutionContextExecutor

trait Bot {

  def token: Token

  implicit val ec: ExecutionContextExecutor = system.dispatcher

  protected lazy val system: ActorSystem = ActorSystem("slack")
  protected lazy val client: SlackRtmClient = SlackRtmClient(token)(system)
  protected lazy val state: RtmState = client.state
  protected lazy val selfId: String = state.self.id
  protected lazy val getCommand: String = "get"
  protected lazy val updateCommand: String = "update"

}
