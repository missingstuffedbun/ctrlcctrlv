import pandas as pd
df = pd.read_csv("./creditcard.csv")
RANDOM_SEED = 314 #used to help randomly select the data points
TEST_PCT = 0.2 # 20% of the data
LABELS = ["Normal","Fraud"]
normal_df = [df.Class == 0] #save normal_df observations into a separate df
fraud_df = [df.Class == 1] #do the same for frauds
df_norm = df
from sklearn.preprocessing import StandardScaler
df_norm['Time'] = StandardScaler().fit_transform(df_norm['Time'].values.reshape(-1, 1))
df_norm['Amount'] = StandardScaler().fit_transform(df_norm['Amount'].values.reshape(-1, 1))
from sklearn.model_selection import train_test_split
train_x, test_x = train_test_split(df_norm, test_size=TEST_PCT, random_state=RANDOM_SEED)
train_x = train_x[train_x.Class == 0] #where normal transactions
train_x = train_x.drop(['Class'], axis=1) #drop the class column
test_y = test_x['Class'] #save the class column for the test set
test_x = test_x.drop(['Class'], axis=1) #drop the class column
train_x = train_x.values #transform to ndarray
test_x = test_x.values
print(test_x.shape, test_y.shape)
import numpy as np
try_x = np.reshape(test_x[10], (1,30))
print(try_x)

import tensorflow as tf


with tf.Session() as sess:
    with tf.gfile.GFile('./autoencoder_fraud.pb', 'rb') as f:
        graph_def = tf.GraphDef()
        graph_def.ParseFromString(f.read())
        # print(graph_def)
        sess.graph.as_default()
        tf.import_graph_def(graph_def, name='')
        tensor_name_list = [tensor.name+':0' for tensor in graph_def.node]
        print(tensor_name_list)

        input_name = 'CreditCardInput:0'
        output_name = 'Decoder2/Relu:0'
        x = sess.graph.get_tensor_by_name(input_name)
        y = sess.graph.get_tensor_by_name(output_name)
        pred = sess.run(y, feed_dict={input_name: try_x})
        print(pred)


model = tf.keras.models.load_model('./autoencoder_fraud.h5')
pred = model.predict(try_x)
print(pred)