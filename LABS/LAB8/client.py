import socket
 
def Main():
    host = '10.42.0.116'
    port = 5000
     
    mySocket = socket.socket()
    mySocket.connect((host,port))
     
    message = input(" -> ")
     
    while message != "That's it!":
            mySocket.send(message.encode())
            data = mySocket.recv(1024).decode()
             
            print ('Received from server: ' + data)
             
            message = input(" -> ")
             
    mySocket.close()
 
if __name__ == '__main__':
    Main()
