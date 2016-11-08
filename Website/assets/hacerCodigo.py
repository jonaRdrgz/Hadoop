nombreArchivo = input()
archivo = open(nombreArchivo)
data = ''
for linea in archivo:
    data += ".'" + linea +"'"  
print (data)