package clients

import akka.actor.ActorSystem
import config.Config
import scalaj.http.{Http, HttpRequest, HttpResponse}
import pureconfig.generic.auto._
import slack.SlackUtil
import slack.rtm.SlackRtmClient

import scala.concurrent.ExecutionContextExecutor

object MTMClient {

  lazy val config: Config = pureconfig.loadConfig[Config] match {
    case Left(error) =>
      println("ERROR: " + error.toList.toString())
      sys.exit()
    case Right(success) => success
  }

  val posturl: String = ("https://api.mtm.headstorm.com/api/v1/oauth/token/")
  val geturl: HttpRequest = Http("https://api.mtm.headstorm.com/api/external/v1/consultant_locations/this_week")

  val auth_key: HttpResponse[String] = Http(posturl)
    .param("client_id", config.mtm.clientId)
    .param("client_secret", config.mtm.clientSecret)
    .param("grant_type", "client_credentials") //client_credentials not client_secret
    .param("scope", "api")
    .asString

  val token: String = ""

  implicit val system: ActorSystem = ActorSystem("slack")
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  val client: SlackRtmClient = SlackRtmClient(token)
  val selfId: String= client.state.self.id

  def main(args: Array[String]): Unit = {
    client.onMessage { message =>
      val mentionedIds = SlackUtil.extractMentionedIds(message.text)

      if(mentionedIds.contains(selfId)) {
        client.sendMessage(message.channel, s"<@${message.user}>: ```$auth_key```")
      }
    }
  }

  //  val auth_key = Http(posturl).
  //    postMulti(MultiPart("client_id", "client_secret", "grant_type", "scope")).
  //    postMulti(MultiPart("4", "zE1y8kOSqGzPogc9P0oKERtsTjem6I60bjIbwbCr", "client_credentials", "api")).asString


  //  val post = new HttpPost(posturl)
  ////  post.addHeader("client_id", "4")
  ////  post.addHeader("client_secret", "")
  ////  post.addHeader("grant_type", "client_credentials")
  ////  post.addHeader("scope", "api")
  ////  post.addHeader("Content-type","application/json")
  //
  //
  //  val postclient = HttpClientBuilder.create.build
  //
  //  val nameValuePairs = new ArrayList[NameValuePair](1)
  //  nameValuePairs.add(new BasicNameValuePair("client_id", "4"))
  //  nameValuePairs.add(new BasicNameValuePair("client_secret", ""))
  //  nameValuePairs.add(new BasicNameValuePair("grant_type", "client_credentials"))
  //  nameValuePairs.add(new BasicNameValuePair("scope", "api"))
  //  post.setEntity(new UrlEncodedFormEntity(nameValuePairs))
  //
  //
  ////  Http(posturl).postData(s"${post.setEntity(new UrlEncodedFormEntity(nameValuePairs))}").method("POST").header("content-type","application/json").asString
  //
  //
  ////  val auth_key = postclient.execute(post)
  //  val resp = postclient.execute(post).getEntity.getContent
  ////  val auth_key = scala.io.Source.fromInputStream(resp)
  //  println("--- HEADERS ---")
  ////
  ////  auth_key.getAllHeaders.foreach(arg => println(arg))
  //////////////////

  //pass data as raw json application
  // case class
  //semiautomatic
  //foo asjson
  //proxy to interpret the requests - charles proxy
  //circleci file describe how to do the build

}
