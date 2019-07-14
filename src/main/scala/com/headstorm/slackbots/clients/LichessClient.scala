package com.headstorm.slackbots.clients

import com.headstorm.slackbots.config._
import io.circe
import io.circe.generic.auto._
import io.circe.parser._
import scalaj.http.{Http, HttpResponse}
import scribe._

class LichessClient[F[_]]() {

  def challengeUser(userName: String): Either[circe.Error, CreateChallengeResponse] = {

    val postUrl: String = s"${config.clients.lichess.api}/challenge/$userName"
    val apiToken: String = config.clients.lichess.token

    val response: HttpResponse[String] = Http(postUrl)
      .postForm(Seq(("rated", "true")))
      .headers(Seq(("Authorization",s"Bearer $apiToken"),("Accept", "application/json"), ("Content-Type", "application/x-www-form-urlencoded")))
      .asString

    response.logger.info(s"Successful response from Lichess challenge create: ${response.body}")

    decode[CreateChallengeResponse](response.body) match {
      case Left(error) =>
        error.logger.error(s"Error calling lichess api: ${error.toString}")
        Left(error)
      case Right(success) => Right(success)
    }
  }
}

final case class TimeControl(
  increment: Option[Double],
  limit: Option[Double],
  show: Option[String],
  `type`: Option[String],
)

final case class Variant(
  key: Option[String],
  name: Option[String],
  short: Option[String],
)

final case class Challenger(
  id: String,
  name: Option[String],
  online: Option[Boolean],
  provisional: Option[Boolean],
  rating: Option[Double],
  title: Option[String],
  lag: Option[Long]
)

final case class Perf(
  icon: Option[String],
  name: Option[String],
)

final case class Challenge(
  id: String,
  color: Option[String],
  direction: Option[String],
  timeControl: Option[TimeControl],
  variant: Option[Variant],
  challenger: Option[Challenger],
  destUser: Option[Challenger],
  perf: Option[Perf],
  rated: Option[Boolean],
  speed: Option[String],
  status: Option[String],
)

final case class CreateChallengeResponse(
  challenge: Challenge,
  socketVersion: Option[Int]
)