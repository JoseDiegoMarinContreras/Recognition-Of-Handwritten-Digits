import numpy as np
#import matplotlib.pyplot as plt
from PIL import Image
#from scipy.misc import toimage

#Importar librerias necesarias...
#from keras import optimizers
from keras.models import model_from_yaml


path = '../UserInterface/temp.jpg'

img = Image.open(path)
img = img.resize((28, 28))
gray = img.convert('L')

im1 = np.array(gray)

imagenX = im1.reshape(1, 1, 28, 28)
imagenX = imagenX.astype('float32')
imagenX /= 255
'''
plt.figure()
plt.imshow(imagenX[0,0],cmap='gray')
#plt.imshow(imagenX[0,0])
plt.title("Image to look.")
plt.show()
'''
#---Cargar YAML y crear el modelo...-------------------------------------------
yaml_file = open('../Model//model.yaml', 'r')        
loaded_model_yaml = yaml_file.read()        
yaml_file.close()        
model = model_from_yaml(loaded_model_yaml)

# Cargar pesos dentro del nuevo modelo...
model.load_weights("../Model//model.h5")
        
model.compile(loss='categorical_crossentropy',
              optimizer='adam',
              metrics=['accuracy'])

prediction = model.predict(imagenX)
numberPrediction = 0
highestNumber = 0
for i in range(0,10):
    if highestNumber < prediction[0, i]:
        highestNumber = prediction[0, i]
        numberPrediction = i
        
print(str(numberPrediction))
