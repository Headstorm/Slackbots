# Slack Bots

Headstorm's Slack Bots to automate Headstorm operations.

All bots and supporting code are written in Scala and initialized in `Module`

Run bot server locally with `sbt run`

You need to have the environment variables set which are named in the `application.conf` in order to auth with the Slack API

## Bots

Bots are located in the `bots` package

### Standup Bot

Asynchronous standup bot

### MTM Bot

Bot that integrates with the MTM Api.  Get and set consultant locations from Slack.

### Harvest Bot

Bot that integrates with the Harvest API.

### Performance Review Bot

Bot integrates with the Onitamago API to manage performance reviews in Slack

### Chess Bot

Bot that integrates with the Lichess API for Headstormers to create challenges automatically with each other in Slack

### Scala Bot

Provides a real time Scala REPL in Slack!

