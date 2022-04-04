from socket import *

server_name = '192.168.1.148'
server_port = 12000

client_socket = socket(AF_INET, SOCK_STREAM)
client_socket.connect((server_name, server_port))

sentence = input('Input lowercase sentence: ')
client_socket.send(sentence.encode())

upper_sentence = client_socket.recv(4096)
print ('From Server:', upper_sentence.decode())

client_socket.close()
