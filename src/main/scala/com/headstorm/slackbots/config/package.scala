package com.headstorm.slackbots

import pureconfig.generic.auto._
import scribe._

package object config {

  lazy val config: Config = pureconfig.loadConfig[Config] match {
    case Left(error) =>
      error.logger.info(s"Error getting config: ${error.toList}")
      sys.exit()
    case Right(success) => success
  }

}
