package com.headstorm.slackbots.bots

class StandupBot extends Bot {

  client.onMessage { message =>

    val statusTitle: Iterator[String] = Iterator.apply("Feeling", "Yesterday", "Today", "Blocked")
    val userName: String = state.getUserById(message.user).map(_.name).getOrElse("unknown user")
    val sendToChannel: String = message.text.split("\\|").toList.map(i => s"*${statusTitle.next}*\n>$i").mkString(s"*Standup for: ${userName}*\n\n","\n","")

    client.sendMessage("GKSAE1GBY", sendToChannel.mkString)
  }
}
