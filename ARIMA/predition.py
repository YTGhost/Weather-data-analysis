import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

from statsmodels.graphics.tsaplots import plot_acf,plot_pacf#自相关图、偏自相关图
from statsmodels.tsa.stattools import  adfuller as ADF#平稳性检验
from statsmodels.stats.diagnostic import  acorr_ljungbox#白噪声检验
import statsmodels.api as sm#画QQ图，检验一组数据是否服从正态分布
from statsmodels.tsa.arima_model import ARIMA

#导入需要预测的文件
sale=pd.read_csv('./maxmin.csv',index_col=0)


#看一下有多少数据，把tmax里面转换类型为浮点型
"""
sale.info()
print("-----")
sale.tmax=sale.tmax.astype('float')
sale.tmin=sale.tmax.astype('float')
sale.info()
"""



#原始序列的检验
"""时序图"""
"""
plt.figure(figsize=(10,5))
sale.plot()
plt.show()
"""
#发现不是平稳序列



"""切片"""
sale=sale.drop(columns=['tmin'])
sale.index = pd.DatetimeIndex(sale.index).to_period('Y')
#sale.asfreq(freq='1Y')
print(sale)

"""一阶差分"""

d1_sale=sale.diff(periods=1,axis=0).dropna()
d1_sale.asfreq('Y')
plt.figure(figsize=(10,5))

#平稳性检验
print('ADF检验结果为：',ADF(d1_sale.tmax))
#ADF检验结果为： (-4.316103187877291, 0.00041653843198680564...
#<0.05通过平稳性检验，采取一阶差分


#白噪声检验
print(acorr_ljungbox(d1_sale,lags=1))#返回统计量、P值
#返回(array([4.85104183]), array([0.02762944]))<0.05，是非白噪声的

#确定pq
#plot_acf(d1_sale,lags=20).show()
#plot_pacf(d1_sale,lags=20).show()
#ACF截尾，PACF拖尾，用MA方法拟合更好，(pdq)=(011)


"""下面来建模"""

model=ARIMA(sale,(0,1,1)).fit()

print(type(model.forecast(7)))
forecast=pd.Series(model.forecast(7)[0])
forecast=pd.DataFrame(forecast)
forecast.rename(columns={0:'tmax'}, inplace = True)
forecast.to_csv('./forecast.csv',index=True)
print(forecast)