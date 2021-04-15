# Slack Bots

Headstorm's Slack Bots to automate Headstorm operations.

All bots and supporting code are written in Scala and initialized in `Module`

All the bots (right now) are prototypes and none of them have been deployed to a server that will run them permanently.

Run bot server locally with `sbt run`

You need to have the environment variables set which are named in the `application.conf` in order to auth with the Slack API

## Bots

Bots are located in the `bots` package

### Making changes for existing bots
To make any changes you can test them out locally by booting the server on your machine using the above instructions. After completing the changes the server running sbt will need to deployed somewhere to run and have live bots.

### Creating new bots
Slack provides a range of APIs to integrate bots in your work space. Any new apps will need to be registered with Slack https://api.slack.com/apps. All API token and access keys can be obtained through Slack by logging into Headstorm's organization online. 

## Usage Guidelines
All Slack apps need to be registered and distributed by Slack, and can be done with APIs or through their website. All apps are available through the Slack app store and can be configured to work in whichever way the creater/users desire, either by using mention `@myBot` or setting up regular interactioon intervals.

### Standup Bot

Asynchronous standup bot

### Vacation Bot

Send standard vacation messages to the vacation channel, report regularly on who is OOO for the day/week to automatically keep people notified and reduce instances of "Where is Kevin?".  Let's get real, people don't check MTM, but Slack notifications are a easy way to keep the right people in the know.

### PRetro Bot

Send a customizable, standard PRetro to your private advisor channel. View metrics and get reminders on PRetros.

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

