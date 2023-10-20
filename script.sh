#!/bin/bash

echo "Descompactado pacote..."
unzip personalprofile.zip

echo "copiando executavel"
cp home/runner/work/personalprofile/personalprofile/target/\*/personalprofile-1.0-SNAPSHOT-runner /home/github/personalprofile/
echo "Adicionando permissão de execução ao app"
chmod +x personalprofile-1.0-SNAPSHOT-runner
echo "copiando dockerfile..."
cp home/runner/work/personalprofile/personalprofile/src/main/docker/Dockerfile.native /home/github/personalprofile/
echo "removendo diretório extraido..."
rm -R /home/github/personalprofile/home
echo "removendo arquivo compactado..."
rm personalprofile.zip
echo "realizando o build da imagem docker"
docker build -f Dockerfile.native -t quarkus/personalprofile .
echo "executando container com o executavel"
docker run -ti --rm -p 8080:8080 quarkus/personalprofile
