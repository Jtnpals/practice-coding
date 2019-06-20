from __future__ import absolute_import, division, print_function, unicode_literals

import numpy as np


from sklearn.preprocessing import minmax_scale, MinMaxScaler, RobustScaler

import pandas as pd


def append_data(filename):
    total_data = pd.DataFrame(columns={'neural1', 'neural2', 'neural3', 'neural4'})
    for i in range(1, 6):
        data = pd.read_csv(f"data/{filename}_{i}.csv", header=None,
                           names=['time', 'type', 'neural1', 'neural2', 'neural3', 'neural4', 'test1', 'test2'])
        dropped_data = data.drop(['time', 'type', 'test1', 'test2'], axis='columns')
        dropped_data = dropped_data[data['type'].str.contains('Person0/eeg')]
        dropped_data = dropped_data[:dropped_data.shape[0] % 128 * -1]
        total_data = total_data.append(dropped_data, sort=False)

    print(total_data)
    total_data.to_csv(f"./{filename}_total.csv", header=None, index=False)


# append_data('nothing')

data = pd.read_csv("./blink_total.csv", header=None)
print (data.values)
X_MinMax_scaled = minmax_scale(data, axis=0, copy=True)
print(X_MinMax_scaled)

X_MinMax_train = MinMaxScaler().fit_transform(data)
print(X_MinMax_train)

X_Robust_train = RobustScaler().fit_transform(data)
print(X_Robust_train)


np.savetxt("normalized.csv", X_Robust_train, delimiter=",")
print(pd.DataFrame(X_Robust_train))


# data = np.loadtxt("data/blink_1.csv", delimiter=',', dtype='np.float32')
# data_num=int(normalized_data.shape[0]/128)
# vector3_data= normalized_data.reshape([data_num,128,4])
# zer=np.ones(data_num,dtype='int32')*2
# print(zer)
# print(len(vector3_data[0]))
#
# print(vector3_data)