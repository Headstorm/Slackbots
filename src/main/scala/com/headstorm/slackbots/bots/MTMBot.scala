package com.headstorm.slackbots.bots

import com.headstorm.slackbots.clients.MTMClient
import com.headstorm.slackbots.config._
import slack.SlackUtil

/**
  * Bot for getting and creating MTM consultant locations
  */
class MTMBot[F[_]](mtmClient: MTMClient[F]) extends Bot {

  lazy val token: Token = config.slackbots.mtm.token
  lazy val channel: String = config.slackbots.mtm.channelId

  client.onMessage{ message =>
//    val mentionIds = SlackUtil.mentionsId(message.text, channel)
    val mentionIds = SlackUtil.extractMentionedIds(message.text)
//    val mentionIds = SlackUtil.isDirectMsg(message)
    if (mentionIds.contains(selfId)) {
      client.sendMessage(message.channel, s"<@${message.user}>:```${mtmClient.getConsultantLocations.right}```")
    }
//    else if (mentionIds.equals(updateCommand)){
//      client.sendMessage(message.channel, s"<@${message.user}>:`updating`")
//    }

    else
      {
        client.sendMessage(message.channel, s"<@${message.user}>: can't help :( ")
      }


  }

//  def command(c: String): ActorRef = c match {
//    case "get" =>
//      client.onMessage { message =>
//        val mentionedIds= SlackUtil.extractMentionedIds(message.text)
//        mtmClient.getConsultantLocations
//        if(mentionedIds.contains(selfId)) {
//          client.sendMessage(message.channel, s"<@${message.user}>:```${mtmClient.getConsultantLocations.right}```")
//        }
//        Right("get")
//
//      }
//    case "set" =>
//      client.onMessage { message =>
//        //    val userName: String = state.getUserById(message.user).map(_.name).getOrElse("unknown user")
//        val mentionedIds= SlackUtil.extractMentionedIds(message.text)
//        if(mentionedIds.contains(selfId)) {
//          client.sendMessage(message.channel, s"<@${message.user}>:```got it```")
//        }
//        Right("set")
//
//      }
//
//  }

//  client.onMessage { message =>
////    val userName: String = state.getUserById(message.user).map(_.name).getOrElse("unknown user")
//    val mentionedIds= SlackUtil.extractMentionedIds(message.text)
//    mtmClient.getConsultantLocations
//    if(mentionedIds.contains(selfId)) {
//      client.sendMessage(message.channel, s"<@${message.user}>:```${mtmClient.getConsultantLocations.right}```")
//    }
////    mtmClient.setConsultantLocation(userName)
////    //TODO: add commands
//
//  }

}
