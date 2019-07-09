package run

import java.net.HttpURLConnection

import akka.actor.ActorSystem
import com.sun.corba.se.spi.transport.ReadTimeouts
import javax.print.DocFlavor.URL
import play.api.libs.json.Json
import slack.SlackUtil
import slack.rtm.SlackRtmClient
import scalaj.http.{Http, MultiPart, Token}

object Example extends App {

  val posturl = Http("https://api.mtm.headstorm.com/api/v1/oauth/token/")
  val geturl = Http("https://api.mtm.headstorm.com/api/external/v1/consultant_locations/this_week")



  val auth_key = Http("https://api.mtm.headstorm.com/api/v1/oauth/token/").postMulti(MultiPart(
    "4", "zE1y8kOSqGzPogc9P0oKERtsTjem6I60bjIbwbCr", "client_credentials", "api"
  )).asString

//  val auth_key = posturl
//    .param("client_id", "4")
//    .param("client_secret", "zE1y8kOSqGzPogc9P0oKERtsTjem6I60bjIbwbCr")
//    .param("grant_type", "client_credentials")
//    .param("scope", "api")
//    .asString

  println("auth key: ", auth_key)





  val token = ""


  implicit val system = ActorSystem("slack")
  implicit val ec = system.dispatcher

  val client = SlackRtmClient(token)
  val selfId = client.state.self.id




  client.onMessage { message =>
    val mentionedIds = SlackUtil.extractMentionedIds(message.text)

    if(mentionedIds.contains(selfId)) {
      client.sendMessage(message.channel, s"<@${message.user}>: ```$auth_key```")
    }
  }



//////////////////////////////////////////////////////////





}
