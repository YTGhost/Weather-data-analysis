import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

from statsmodels.graphics.tsaplots import plot_acf,plot_pacf#自相关图、偏自相关图
from statsmodels.tsa.stattools import  adfuller as ADF#平稳性检验
from statsmodels.stats.diagnostic import  acorr_ljungbox#白噪声检验
from statsmodels.tsa.arima_model import ARIMA

#导入需要预测的文件
sale=pd.read_csv('./maxmin.csv',index_col=0)


#把里面转换类型为浮点型
#sale.tmax=sale.tmax.astype('float')
#sale.tmin=sale.tmax.astype('float')


#原始序列的检验
"""时序图"""
"""
plt.figure(figsize=(10,5))
sale.plot()
plt.show()
"""
#发现不是平稳序列



"""切片"""
sale_max=sale.drop(columns=['tmin','tmid'])
sale_min=sale.drop(columns=['tmax','tmid'])
sale_mid=sale.drop(columns=['tmax','tmin'])
sale_max.index = pd.DatetimeIndex(sale_max.index).to_period('Y')
sale_min.index = pd.DatetimeIndex(sale_min.index).to_period('Y')
sale_mid.index = pd.DatetimeIndex(sale_mid.index).to_period('Y')




"""计算d"""
tmax_d=0
tmin_d=0
tmid_d=0

for i in range(1,3):
    """i阶差分"""
    if tmax_d==0:
        d_sale_max=sale_max.diff(periods=1,axis=0).dropna()
        for k in range(1,i):
            d_sale_max=d_sale_max.diff(periods=1,axis=0).dropna()
        d_sale_max.asfreq('Y')
    if tmin_d == 0:
        d_sale_min=sale_min.diff(periods=1,axis=0).dropna()
        for k in range(1,i):
            d_sale_min=d_sale_min.diff(periods=1,axis=0).dropna()
        d_sale_min.asfreq('Y')
    if tmid_d == 0:
        d_sale_mid=sale_mid.diff(periods=1,axis=0).dropna()
        for k in range(1,i):
            d_sale_mid=d_sale_mid.diff(periods=1,axis=0).dropna()
        d_sale_mid.asfreq('Y')
    #平稳性检验

    if ADF(d_sale_max.tmax)[1]<0.05 and acorr_ljungbox(d_sale_max,lags=1)[1]<0.05:
        tmax_d = i
    if ADF(d_sale_min.tmin)[1]<0.05 and acorr_ljungbox(d_sale_min,lags=1)[1]<0.05:
        tmin_d = i
    if ADF(d_sale_mid.tmid)[1]<0.05 and acorr_ljungbox(d_sale_mid,lags=1)[1]<0.05:
        tmid_d = i
    #ADF检验结果为： (-4.316103187877291, 0.00041653843198680564...
    #<0.05通过平稳性检验，采取一阶差分
    # 白噪声检验
    # print(acorr_ljungbox(d1_sale_max,lags=1))#返回统计量、P值
    # 返回(array([4.85104183]), array([0.02762944]))<0.05，是非白噪声的

"""
if tmax_d==0:
    tmax_d = 1
if tmin_d==0:
    tmin_d = 1
if tmid_d==0:
    tmid_d = 1
"""



#确定pq
#plot_acf(d1_sale,lags=20).show()
#plot_pacf(d1_sale,lags=20).show()
#ACF截尾，PACF拖尾，用MA方法拟合更好，(pdq)=(011)





"""下面来建模"""

model_max=ARIMA(sale_max,(0,tmax_d,1)).fit()
model_min=ARIMA(sale_min,(0,tmin_d,1)).fit()
model_mid=ARIMA(sale_min,(0,tmid_d,1)).fit()

forecast_max=pd.Series(model_max.forecast(7)[0])
forecast_min=pd.Series(model_min.forecast(7)[0])
forecast_mid=pd.Series(model_mid.forecast(7)[0])


forecast_max=pd.DataFrame(forecast_max)
forecast_min=pd.DataFrame(forecast_min)
forecast_mid=pd.DataFrame(forecast_mid)

forecast_max.rename(columns={0:'tmax'}, inplace = True)
forecast_min.rename(columns={0:'tmin'}, inplace = True)
forecast_mid.rename(columns={0:'tmid'}, inplace = True)

forecast_max.index=[i for i in range(2013,2020)]
forecast_min.index=[i for i in range(2013,2020)]
forecast_mid.index=[i for i in range(2013,2020)]

plt.figure(figsize=(10,5))
forecast_max.plot()
forecast_min.plot()
forecast_mid.plot()
plt.show()

forecast=forecast_max
forecast['tmin']=forecast_min['tmin']
forecast['tmid']=forecast_mid['tmid']
forecast.to_csv('./forecast.csv',index=True)
print(forecast)