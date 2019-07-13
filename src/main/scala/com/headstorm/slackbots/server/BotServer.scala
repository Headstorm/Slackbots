package com.headstorm.slackbots.server

import cats.effect.{ExitCode, IO, IOApp}
import com.headstorm.slackbots.config._
import org.http4s.HttpRoutes
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import scribe._

object BotServer extends IOApp {

  private val mod = new Module[IO]()
  private val routes: HttpRoutes[IO] = mod.httpService

  override def run(args: List[String]): IO[ExitCode] = {

    this.logger.info("Starting SlackBots Server")

    BlazeServerBuilder[IO]
      .bindHttp(
        config.server.port,
        config.server.host
      )
      .withHttpApp(routes.orNotFound)
      .serve
      .compile
      .lastOrError
  }

}
