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




  client.onMessage { message =>
    val mentionedIds = SlackUtil.extractMentionedIds(message.text)

    if(mentionedIds.contains(selfId)) {
      client.sendMessage(message.channel, s"<@${message.user}>: <123>")
    }
  }



//////////////////////////////////////////////////////////





}
