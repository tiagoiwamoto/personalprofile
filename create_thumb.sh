#!/bin/bash

IMAGEM=$1
EXTENSAO=$2
VERTICAL=$3

# Inciando conversao de imagem

if[$VERTICAL -eq 0]; then
  echo "Convertendo uma imagem na horizontal"
  convert -thumbnail 250x $IMAGEM $IMAGEM_th.$EXTENSAO
else
  echo "Convertendo uma imagem na vertical"
  convert -thumbnail x200 $IMAGEM $IMAGEM_th.$EXTENSAO
end

# Conversao de imagem finalizada com sucesso
echo "Conversao realizada com sucesso ${IMAGEM}"