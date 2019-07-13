package com.headstorm.slackbots.server.services

import cats.data.NonEmptyList
import cats.effect._
import cats.implicits._
import org.http4s._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

class BotService[F[_]](implicit F: Sync[F]) {

  object dsl extends Http4sDsl[F]
  import dsl._

  def routes: HttpRoutes[F] = {
    val value: NonEmptyList[HttpRoutes[F]] = NonEmptyList
      .of(
        botService
      )
    value.reduceK
  }

  val botService: HttpRoutes[F] = HttpRoutes.of[F] {
    case GET -> Root / "message" / message =>
      Ok(message)
  }

}
