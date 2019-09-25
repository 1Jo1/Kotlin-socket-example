import model.Message
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

/**
 * This class handles the connection in its own thread.
 *
 * NOTE: To run this class in its own thread, I extended from Thread. You can do this but it's not best practice in
 * kotlin. Take a look to coroutines for asynchronous task.
 */
class ServerConnection(val socket: Socket) : Thread() {
    val output: PrintWriter = PrintWriter(socket.getOutputStream(), true)
    val input: BufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))

    /**
     * Starting the listening process in the background. See function run()
     */
    init{
        start()
    }

    /**
     * Sending a message to the server
     */
    fun sendMessage(message: Message) {
        output.println(message.toString())
    }

    /**
     * Sending a string to the server
     */
    fun sendMessage(message: String) {
        output.println(message)
    }

    /**
     * Listen to the incoming traffic
     */
    private fun listen() {
        while (socket.isConnected) {
            var input = input.readLine()
            if (input !== null) {
                println("[${socket.inetAddress}] $input")
            }
        }
    }

    /**
     * Start listener in its own thread.
     * In order to start use start()
     */
    override fun run() {
        listen()
    }

}