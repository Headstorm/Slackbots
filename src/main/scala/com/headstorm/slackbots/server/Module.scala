package com.headstorm.slackbots.server

import cats.effect._
import cats.syntax.semigroupk._
import com.headstorm.slackbots.bots.{MTMBot, StandupBot}
import com.headstorm.slackbots.server.services.{BotService, DiagnosticService}
import org.http4s.HttpRoutes

/** Modular constructor for services
  *
  * @param F: The application type, usually IO
  * @tparam F: Same as F above
  */
class Module[F[_]](
  implicit F: ConcurrentEffect[F]
) {

  private val botService = new BotService().routes
  private val diagnosticService = new DiagnosticService().routes

  val httpService: HttpRoutes[F] = botService <+> diagnosticService

  //Initialize Headstorm Bots
  new StandupBot
  new MTMBot
}
