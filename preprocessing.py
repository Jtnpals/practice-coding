from __future__ import absolute_import, division, print_function, unicode_literals

import numpy as np

from sklearn.preprocessing import minmax_scale, MinMaxScaler, RobustScaler

import pandas as pd

# Hyperparameters
seq_len = 128


def append_data(filename):
    total_data = pd.DataFrame(columns={'neural1', 'neural2', 'neural3', 'neural4'})
    for i in range(1, 6):
        data = pd.read_csv(f"data/{filename}_{i}.csv", header=None,
                           names=['time', 'type', 'neural1', 'neural2', 'neural3', 'neural4', 'test1', 'test2'])
        dropped_data = data.drop(['time', 'type', 'test1', 'test2'], axis='columns')
        dropped_data = dropped_data[data['type'].str.contains('Person0/eeg')]
        dropped_data = dropped_data[:dropped_data.shape[0] % seq_len * -1]
        total_data = total_data.append(dropped_data, sort=False)

    total_data.to_csv(f"./{filename}_total.csv", header=None, index=False)


def normalize_data(filename):
    data = pd.read_csv(f"./{filename}.csv", header=None)
    robust_data = RobustScaler().fit_transform(data)
    np.savetxt(f"./normalized_data/normalized_{filename}.csv", robust_data, delimiter=",")


def combine_data(*filename):
    data_len_list = []
    total_list = []
    total_data = pd.DataFrame(columns={'neural1', 'neural2', 'neural3', 'neural4'})
    for i in filename:
        data = pd.read_csv(f'./normalized_data/normalized_{i}.csv', names=['neural1', 'neural2', 'neural3', 'neural4'])
        data_len = int((data.shape[0] + 1) / seq_len)
        data_len_list.append(data_len)
        total_data = total_data.append(data, sort=False)
    for i, j in zip(range(0, 6), data_len_list):
        zero = np.ones(j, dtype='int32') * i
        total_list.extend(zero)
    print(total_list)
    print(total_data)


combine_data('blink_total', 'chewing_total', 'clench_total', 'nod_total', 'nothing_total')
# for i in ['blink_total', 'chewing_total', 'clench_total', 'nod_total', 'nothing_total']:
#     normalize_data(i)

# data = pd.read_csv("./blink_total.csv", header=None)
# print(data.values)

# X_MinMax_scaled = minmax_scale(data, axis=0, copy=True)
# print(X_MinMax_scaled)
#
# X_MinMax_train = MinMaxScaler().fit_transform(data)
# print(X_MinMax_train)

# normalized_data = RobustScaler().fit_transform(data)
# print(normalized_data)

# np.savetxt("normalized.csv", normalized_data, delimiter=",")
# print(pd.DataFrame(normalized_data))
#
# data_len = int(normalized_data.shape[0] / seq_len)
# vector3_data = normalized_data.reshape([data_len, seq_len, 4])
# zer = np.ones(data_len, dtype='int32') * 0

# data = np.loadtxt("data/blink_1.csv", delimiter=',', dtype='np.float32')

# vector3_data= normalized_data.reshape([data_num,128,4])

# print(zer)
# print(len(vector3_data[0]))
#
# print(vector3_data)
