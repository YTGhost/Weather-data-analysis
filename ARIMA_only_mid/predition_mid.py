import pandas as pd
import numpy as np
from scipy import  stats
import matplotlib.pyplot as plt
import statsmodels.api as sm
from statsmodels.graphics.api import qqplot

from statsmodels.tsa.stattools import  adfuller as ADF#平稳性检验
from statsmodels.stats.diagnostic import  acorr_ljungbox#白噪声检验
from statsmodels.tsa.arima_model import ARIMA

def cal_d(df):
    if ADF(df.tmid)[1] < 0.05 or acorr_ljungbox(df, lags=1)[1] < 0.05:
        return 0
    d=1
    d_df=df.diff(periods=1,axis=0).dropna()
    #平稳性检验、白噪声检验
    while ADF(d_df.tmid)[1] >= 0.05 or acorr_ljungbox(d_df, lags=1)[1] >= 0.05:
        d_df = d_df.diff(periods=1, axis=0).dropna()
        d=d+1
        if d>=2 :
            return 2
    return d

def cal_p_q(df,pmax,qmax,p,q,d):
    bic_matrix=[]
    m=1
    for p in range(pmax+1):
        tmp=[]
        for q in range(qmax+1):
            try:
                tmp.append(ARIMA(df,(p,d,q)).fit().bic)
                print(m)
                m=m+1
            except:
                tmp.append(None)
        bic_matrix.append(tmp)
    #根据矩阵，找p,q
    min = 0
    for i in range(0, pmax + 1):
        for j in range(0, qmax + 1):
            if bic_matrix[i][j]:
                if bic_matrix[i][j] < min or min == 0:
                    min = bic_matrix[i][j]
                    p, q = i, j
    return p,q


#导入文件
df = pd.read_csv('./from/lasa.csv',index_col=0)
df.index=pd.DatetimeIndex(df.index).to_period('D')

#时序图
plt.figure(figsize=(10,5))
df.tail(20).plot()
plt.show()#时序图
plt.figure(figsize=(10,5))
df.tail(20).plot()
plt.show()
#计算d
d=cal_d(df)
print(d)

#int(len(df)/10)
#计算p,q
p,q=cal_p_q(df,8,8,0,0,d)
#print(p)
#print(q)
#建模
model=ARIMA(df,(3,1,3)).fit()
#预测
forecast=pd.DataFrame(model.forecast(7)[0])

#预测结果规范化
forecast.rename(columns={0:'tmid'},inplace=True)
forecast.index=[i for i in range(7)]
plt.figure(figsize=(10,5))
forecast.plot()
plt.show()
#写入文件
forecast.to_csv('./forecast/forecast_lasa.csv',index=True)
print(forecast)
