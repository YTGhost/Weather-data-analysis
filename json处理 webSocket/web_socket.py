'''import os,socket,threading
class SendFile():
    def __init__(self,host='127.0.0.1',port=90,filename=''):
        self.address=(host,port)
        self.filename=filename
    def send(self):
        s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        s.connect(self.address)
        send_file = os.path.normcase(self.filename)
        try:
            f=open(self.filename,'rb')
            #print(f)
            send_file_Thread=SendFileThread(s,f)
            send_file_Thread.start()
        except IOError:
            print('error')
class SendFileThread(threading.Thread):
    def __init__(self,sock,file):
        threading.Thread.__init__(self)
        self.sock=sock
        self.file=file
    def run(self):
        print('fineName is'+self.file.name)
        BUFFERSIZE=1024
        count=0
        while True:
            fileData=self.file.read(BUFFERSIZE)
            if not fileData:
                print('no data')
                break
            self.sock.send(fileData)
        print("sent file ok")
if __name__=='__main__':
        send = SendFile(fileName=self.data_type + '.png')
        send.send()'''
import socket,os,threading
class SendFile():
    def __init__(self, serverAdd="127.0.0.1", serverPort=9000, fileName=""):
        self.address = (serverAdd, serverPort)
        self.filename = fileName
    def send(self):
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect(self.address)
        send_file = os.path.normcase(self.filename)
        try:
            f = open(send_file, "rb")
            print(f)
            send_file_thread = SendFileThread(s, f)
            send_file_thread.start()
        except IOError:
            print(" error!!")
class SendFileThread(threading.Thread):
    def __init__(self, sock, file):
        threading.Thread.__init__(self)
        self.sock = sock
        self.file = file
    def run(self):
        print("file name is =="+self.file.name)
        BUFFERSIZE = 1024
        count = 0
        while True:
            file_data = self.file.read(BUFFERSIZE)
            if not file_data:
                print("no data find")
                break
            self.sock.send(file_data)
        print("sent file ok")
