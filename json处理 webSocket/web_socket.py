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
class SendFile(threading.Thread):#继承多线程
    def __init__(self, serverAdd="127.0.0.1", serverPort=9000, fileName=""):#初始化
        threading.Thread.__init__(self)
        self.address = (serverAdd, serverPort)
        self.filename = fileName
    def run(self):
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.connect(self.address)#连接端口
        send_file = os.path.normcase(self.filename)
        try:
            f = open(send_file, "rb")#读取文件
            while True:#发送数据
                strb = f.read(1024)
                if strb==b"":#判断是否发完
                    break;
                socket.sendto(strb, self.address)#发送

        except IOError:
            print(" error!!")

