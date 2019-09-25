import java.net.Socket
import java.util.*

fun main() {
    val server = ServerConnection(Socket("localhost", 9191))
    val input = Scanner(System.`in`)
    while (true) {
        var send: String = input.nextLine()
        server.sendMessage(send)
    }
}