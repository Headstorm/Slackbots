package run

import java.net.HttpURLConnection

import akka.actor.ActorSystem
import com.sun.corba.se.spi.transport.ReadTimeouts
import javax.print.DocFlavor.URL
import play.api.libs.json.Json
import slack.SlackUtil
import slack.rtm.SlackRtmClient
import scalaj.http._

object Example extends App {
  val token = ""
  val OAuth = ""
  implicit val system = ActorSystem("slack")
  implicit val ec = system.dispatcher

  val client = SlackRtmClient(token)
  val selfId = client.state.self.id

//  val response: HttpResponse[Map[String,String]] = Http("http://foo.com").execute(parser = {inputStream =>
//    Json.parse[Map[String,String]](inputStream)
//  })



  client.onMessage { message =>
    val mentionedIds = SlackUtil.extractMentionedIds(message.text)

    if(mentionedIds.contains(selfId)) {
      client.sendMessage(message.channel, s"<@${message.user}>: <123>")
    }
  }



//  def get(url: String, connectTimeout: Int = 5000, readTimeout: Int = 5000, requestMethod: String = "GET") = {
//    import java.net.{URL, HttpURLConnection}
//    val connection = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
//    connection.setConnectTimeout(connectTimeout)
//    connection.setReadTimeout(readTimeout)
//    connection.setRequestMethod(requestMethod)
//    val inputStream = connection.getInputStream
//    val content = io.Source.fromInputStream(inputStream).mkString
//    if (inputStream != null) inputStream.close
//    content
//
//  }
//
//  try {
//    val content = get("https://mtm.headstorm.com/api/external/v1/consultant_locations/this_week")
//    println(content)
//    client.onMessage { message =>
//      val mentionedIds = SlackUtil.extractMentionedIds(message.text)
//
//      if(mentionedIds.contains(selfId)) {
//        client.sendMessage(message.channel, s"<@${message.user}>: <$content>")
//      }
//    }
//  } catch {
//    case ioe: java.io.IOException =>  // handle this
//    case ste: java.net.SocketTimeoutException => // handle this
//  }








//  val token = "xoxb-50834019075-663790820545-qcTfOvAwkbG3NOOnZqpgq9ZZ"
//  implicit val system = ActorSystem("slack")
//  implicit val ec = system.dispatcher
//
//  val url = "/api/external/v1/consultant_locations/this_week"
//  val result = scala.io.Source.fromURL(url).mkString
//  println(result)
//
//  val client = SlackRtmClient(token)
//  val selfId = client.state.self.id
//
//  client.onMessage { message =>
//    val mentionedIds = SlackUtil.extractMentionedIds(message.text)
//
//    if(mentionedIds.contains(selfId)) {
//      client.sendMessage(message.channel, s"<@${message.user}>: <$result>")
//    }
//  }
//////////////////////////////////////////////////////////





}
