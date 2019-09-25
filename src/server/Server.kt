package server

import ServerConnection
import java.net.ServerSocket
import java.net.Socket

/**
 * The server.Server manages all connection to the clients and creates a connection to them when requested.
 */
class Server(port: Int) {
    private val serverSocket: ServerSocket = ServerSocket(port)
    private val clients = mutableListOf<ServerConnection>()
    private val acceptingNewSockets = true
    private val onConnectedCallback = mutableListOf(fun(connection: ServerConnection) {})

    /** ToDo: Fix crappy list **/

    init {
        listen()
    }

    /**
     * Waiting for clients in order to create a connection.
     * Connection will be added to the clients variable.
     */
    private fun listen() {
        println("Listen on port ${serverSocket.localPort}")
        while (acceptingNewSockets) {
            var socket: Socket = serverSocket.accept()
            var connection: ServerConnection = ServerConnection(socket)
            clients.add(connection)
            println("${socket.inetAddress} Connected")

            for (callback in onConnectedCallback) {
                callback(clients[clients.size - 1])
            }
        }
    }
}

