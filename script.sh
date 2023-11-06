#!/bin/bash

echo "Descompactado pacote..."
unzip personalprofile.zip

echo "copiando executavel"
cp home/runner/work/personalprofile/personalprofile/target/\*/personalprofile-1.0-SNAPSHOT-runner /home/github/personalprofile/
echo "Adicionando permissao de execucao ao app"
chmod +x personalprofile-1.0-SNAPSHOT-runner
echo "copiando dockerfile..."
cp home/runner/work/personalprofile/personalprofile/src/main/docker/Dockerfile.native /home/github/personalprofile/
echo "copiando script para gerar thumb"
cp home/runner/work/personalprofile/personalprofile/create_thumb.sh /home/github/personalprofile/
echo "removendo diretorio extraido..."
rm -R /home/github/personalprofile/home
echo "removendo arquivo compactado..."
rm personalprofile.zip
echo "realizando o build da imagem docker"
docker build -f Dockerfile.native -t quarkus/personalprofile .
echo "parando e removendo container anterior"
docker ps -q --filter name=personalprofile | xargs -r docker stop
docker ps -aq --filter name=personalprofile | xargs -r docker rm
echo "executando container com o executavel"
docker run --name personalprofile -d --network=host -i --env POSTGRES_USERNAME="${POSTGRES_USERNAME}" --env POSTGRES_PASSWORD="${POSTGRES_PASSWORD}" -v /home/github/files:/work/files -p 8080:8080 quarkus/personalprofile
echo "removendo dockerfile e executavel"
rm Dockerfile.native personalprofile-1.0-SNAPSHOT-runner
rm -R *.so
echo "processo finalizado"