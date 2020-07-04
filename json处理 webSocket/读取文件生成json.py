import numpy as np
import pandas as pd
from scipy import  stats
import matplotlib.pyplot as plt
import statsmodels.api as sm
from statsmodels.graphics.api import qqplot
import web_socket
class processData():
    def __init__(self,data,predict_year,typeData):
        self.data=data
        self.predict_year=predict_year
        self.typeData=typeData
    def process_min_max(self):
        if self.typeData == 'max':
            max_data=self.data['tmax']
        elif self.typeData == 'min':
            max_data=self.data['tmin']
        data_year=self.data['data']
        begin_year=data_year[0:1].dt.year
        end_year = data_year[-1:].dt.year
        predict_month = data_year[0:1].dt.month
        predict_day = data_year[0:1].dt.day
        max_data = np.array(max_data, dtype=np.float)
        max_data= pd.Series(max_data)
        max_data.index = pd.Index(sm.tsa.datetools.dates_from_range(str(begin_year.values[0]), str(end_year.values[0])))
        arma_mod76 = sm.tsa.ARMA(max_data, (7, 6)).fit()
        predict_end_year = end_year.values[0] + self.predict_year
        predict_dta = arma_mod76.predict(str(end_year.values[0]), str(predict_end_year), dynamic=True)
        print(predict_dta)

    ''' print(json_date)
    fig, ax = plt.subplots(figsize=(12, 8))
    ax = max_data.ix[str(begin_year.values[0]):].plot(ax=ax)
  arma_mod76.plot_predict(str(end_year.values[0]), str(predict_end_year), dynamic=True, ax=ax,
                                  plot_insample=False)
    #fig = plt.gcf()
    #plt.show()
    plt.savefig(self.data_type+'.png', dpi=100)
    #send file
    send = SendFile(fileName=self.data_type+'.png')
    send.send()
'''
    #if __name__=='__main__':
       # p = ProcessData(data, 10, 'min')
       #p.process_minmax()


