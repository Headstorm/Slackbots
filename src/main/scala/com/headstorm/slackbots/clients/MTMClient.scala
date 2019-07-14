package com.headstorm.slackbots.clients

import com.headstorm.slackbots.config._
import io.circe
import io.circe.generic.auto._
import io.circe.parser._
import scalaj.http.{Http, HttpResponse}
import scribe._

class MTMClient[F[_]]() {

  private val postUrl: String = s"${config.clients.mtm.api}/v1/oauth/token/"
  //TODO: private val getUrl: HttpRequest = Http(s"${config.clients.mtm.api}/external/v1/consultant_locations/this_week")

  def getAccessToken: Either[circe.Error, String] = {
    val response: HttpResponse[String] = Http(postUrl)
      .postData(s"""{"client_id":${config.clients.mtm.clientId}, "client_secret": "${config.clients.mtm.clientSecret}",
                   | "grant_type":"client_credentials", "scope":"api"}""".stripMargin)
      .headers(Seq(("Accept", "application/json"), ("Content-Type", "application/json")))
      .asString

    response.logger.info(s"Successful response from MTM Auth: ${response.body}")

    decode[AccessToken](response.body).right.map(_.access_token)
  }

  def getConsultantLocations: Either[Error, String] = {
    ???
  }

  def setConsultantLocation(user: String): Either[Error, String] = {
    ???
  }

}

final case class AccessToken(
  token_type: String,
  expires_in: String,
  access_token: String,
  status: String,
  grant_type: String
)
