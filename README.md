# SGSSI_MINER

El proyecto esta hecho en java, basta con ejecutar el Main para poder utilizarlo.

Este proyecto cuenta con varios metodos.

sha256Digest: obtiene el sha correspondiente pasandole el path del archivo.

writeChecksum: Escribe al final de un fichero su sha256

randomString: Crea im string hexadecimal aleatorio de longitud n

modifyFileToZero: busca una cadena hexadecimal de 8 caracteres para conseguir que el sha256 empiece por un cero

modifyFileToZero1MIN: Busca la mejor cadena hexadecimal de 8 caracteres, es decir, la que consiga mas ceros en 1 minuto.

modifyFileGroup1MinN: Hace lo mismo que el anterior sumandole a la cadena en cuestion el identificador del grupo.

comprobarArchivos: Comprueba que un bloque sea correcto(sea igual que el anterior con su proof of work en hexadecimal+ el grupo)

APUNTES-> en modifyFileToZero1MIN, se pueden hacer algunos ajustes para cambiar su utilidad, si cambiamos la condicion del while podemos utilizar bestzeros(una variable que cuenta la mayor cantidad de ceros encontrada) para crear la condicion de que no pare hasta encontrar un numero concreto de ceros.

Ademas, hay un if que devuelve la cadena necesaria cada vez que se encuentra un sha de 4 ceros, se puede modificar si queremos uno con mas o menos ceros.

MD5 OF CODE:dd6d6d1ee205673906c68f0329bd4a1f


//Parte del codigo se ha extraido del repositorio https://github.com/gorokotkd/SGSSI
