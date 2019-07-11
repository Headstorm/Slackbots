package clients

import config.BotConfig

object MTMClient extends App {

  pureconfig.loadConfig[BotConfig]

  val posturl = ("https://api.mtm.headstorm.com/api/v1/oauth/token/")
  val geturl = Http("https://api.mtm.headstorm.com/api/external/v1/consultant_locations/this_week")

  val auth_key = Http(posturl)
    .postMulti(MultiPart())
    .param("client_id", "4")
    .param("client_secret", "")
    .param("grant_type", "client_secret")
    .param("scope", "api")
    .asString
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
}
