from socket import *
import time

server_name = "192.168.1.148"
server_port = 12000
client_socket = socket(AF_INET, SOCK_DGRAM)

message = input('Input lowercase sentence: ')
client_socket.sendto(message.encode(), (server_name, server_port))
time.sleep(3)
upper_message, server_address = client_socket.recvfrom(4096)

print (upper_message)

client_socket.close()
