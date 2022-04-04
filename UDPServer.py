from socket import *
server_host = ''
server_port = 12000
server_socket = socket(AF_INET, SOCK_DGRAM)
server_socket.bind((server_host, server_port))
print ("The server is ready to receive")

while True:
    message, client_address = server_socket.recvfrom(2048)
    print (client_address)
    upper_message = message.decode().upper()
    server_socket.sendto(upper_message.encode(), client_address)

