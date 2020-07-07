import numpy
import matplotlib.pyplot as plt
from keras.models import Sequential
from keras.layers import Dense
from keras.layers import LSTM
import  pandas as pd
import  os
from keras.models import Sequential, load_model
from sklearn.preprocessing import MinMaxScaler


from numpy import array
from keras.models import Sequential
from keras.layers import LSTM
from keras.layers import Dense


def split_sequence(sequence, n_steps):
    X, y = list(), list()
    for i in range(len(sequence)):
        # find the end of this pattern
        end_ix = i + n_steps
        # check if we are beyond the sequence
        if end_ix > len(sequence)-1:
            break
        # gather input and output parts of the pattern
        seq_x, seq_y = sequence[i:end_ix], sequence[end_ix]
        X.append(seq_x)
        y.append(seq_y)
    return array(X), array(y)


dataframe = pd.read_csv('./from/hainan.csv', usecols=[1], engine='python')
dataset = dataframe.values
# 将整型变为float
dataset = dataset.astype('float32')
#归一化 在下一步会讲解
raw_seq = dataset[:]

print(raw_seq)


#raw_seq = [10, 20, 30, 40, 50, 60, 70, 80, 90]

# choose a number of time steps
n_steps = 7
# split into samples
X, y = split_sequence(raw_seq, n_steps)

# reshape from [samples, timesteps] into [samples, timesteps, features]
n_features = 1
X = X.reshape((X.shape[0], X.shape[1], n_features))
# define model
model = Sequential()
model.add(LSTM(50, activation='relu', input_shape=(n_steps, n_features)))  # 隐藏层，输入，特征维
model.add(Dense(1))
model.compile(optimizer='adam', loss='mse')
# fit model
model.fit(X, y, epochs=3, batch_size=1, verbose=2)  # 迭代次数，批次数，verbose决定是否显示每次迭代
# demonstrate prediction
tes=raw_seq[-7:]

for i in range(7):
    x_input = array(tes)
    x_input = x_input.reshape((1, n_steps, n_features))
    yhat = model.predict(x_input, verbose=0)
    for k in range(6):
        tes[k]=tes[k+1]
    tes[6]=yhat[0][0]

dataF=pd.DataFrame(tes)
dataF.rename(columns={0:'tmid'},inplace=True)
plt.figure(figsize=(10,5))
dataF.plot()
plt.show()

dataF.to_csv('./forecast/hainan_forecast.csv',index=True)
