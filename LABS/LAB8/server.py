import socket
import os

def Main():
    host = "10.42.0.116"
    port = 5000
     
    mySocket = socket.socket()
    mySocket.bind((host,port))
     
    mySocket.listen(1)
    conn, addr = mySocket.accept()
    print ("Connection from: " + str(addr))
    done = False
    
    while True:
        data = conn.recv(1024).decode()
        if not data:
                break
        print ("from connected  user: " + str(data))
         
        data = str(data)
        res = "Sorry dont know the answer!!"
        
        if data == "What is the MAC address of the ethernet adapter?":
            with open('/sys/class/net/eth0/address') as f:
                mac = f.read()
                res = mac
        elif data == "What is the IPv4 address of the interface you are using now to connect to the server?":
            res = "10.42.0.116"
        elif data == "What is the linux kernel version of the BeagleBone?":
            res = "4.9.82-ti-r102"
        elif data == "Does your BeagleBone have a WiFi adaptor connected to it right now?":
            res = "No"
        elif data == "What is the directory in which the script you're running is present?":
            res = os.getcwd()
        elif data == "That's it!":
            res = "Bye"
            done = True
            
        print ("sending: " + str(res))
        conn.send(res.encode())
        
        if done:
            break
             
    conn.close()
     
if __name__ == '__main__':
    Main()
