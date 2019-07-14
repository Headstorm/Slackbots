package com.headstorm.slackbots.bots

import com.headstorm.slackbots.config.config

/**
  * Bot to compile Scala code in Slack, Slack REPL!
  */
class ScalaBot[F[_]]() extends Bot {

  lazy val token: Token = config.slackbots.scalaRepl.token

}