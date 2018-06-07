# AyDS-2018-A2-Projecto-Delta

Arquitectura y Diseño de Sistemas - UNS - Bahia Blanca - 2018

Agustina Superi -
Juan Francisco Moyano -
Matias Massetti

## Agregar repositorio de la libreria 

Para utilizar el submodulo que contiene el servicio externo de BigHugeLabsLib es necesario agregar la dependencia como sub-modulo 
en git.

Para esto se debe ingresar en la terminal el siguiente comando:

```git submodule add https://github.com/juanfmoyano/bhlLIB```

## Uso de la libreria de BigHugeLabsLib (bhlLib)

Para utilizar la libreria se tiene que crear una instancia del servicio. Esto se realiza a traves de un ```ServiceModule```.

Luego se debe conectar al servicio. Esto se realiza por medio de un metodo ```connect()```, donde se accede a las facilidades de la API.

Para consultar por un termino en la API se utiliza el comando:

```public String getMeaning(String term)```

Al metodo se le pasa un ```String``` que corresponde al ```término``` que se quiere buscar en el diccionario provisto
Si el servicio encuentra un ```significado``` para dicho término, lo devuelve. En caso contrario, el metodo devuelve un valor ```null```
