package com.headstorm.slackbots.clients

import com.headstorm.slackbots.config._
import scalaj.http.{Http, HttpRequest, HttpResponse}
import scribe._

class MTMClient[F[_]](slackClient: SlackClient[F]) {

  val postUrl: String = s"${config.mtm.api}/v1/oauth/token/"
  val getUrl: HttpRequest = Http(s"${config.mtm.api}/external/v1/consultant_locations/this_week")

  val response: HttpResponse[String] = Http(postUrl)
    .postData(s"""{"client_id":${config.mtm.clientId}, "client_secret": "${config.mtm.clientSecret}",
                 | "grant_type":"client_credentials", "scope":"api"}""".stripMargin)
    .headers(Seq(("Accept", "application/json"), ("Content-Type", "application/json")))
    .asString

  response.logger.info(s"Successful response: ${response.body}")

  slackClient.sendMessageToSlackChannel(response.body)

}
