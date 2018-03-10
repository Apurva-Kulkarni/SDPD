import socket
import os
import subprocess

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(('', 12345))
server_socket.listen(5)

while True:
    (client_socket, address) = server_socket.accept()
    print("Client:", str(address))
    recv_msg = client_socket.recv(1024).decode('ascii')
    print("Recieved:", recv_msg)
    # ret = os.system(recv_msg)
    ret = subprocess.call(recv_msg.split(' '))
    client_socket.send(("Return value for " + recv_msg + " was " + str(ret)).encode('ascii'))
    client_socket.close()
