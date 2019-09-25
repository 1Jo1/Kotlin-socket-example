package model

import ServerConnection

data class Chat(val server: ServerConnection, val username: String, val messages: Collection<Message>)