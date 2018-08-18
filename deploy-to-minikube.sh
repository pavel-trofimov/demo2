#!/usr/bin/env bash
./mvnw package -f pom.xml
eval $(minikube docker-env)
docker build -t demo2:latest  .
helm del --purge demo2
helm dep build ./helm
helm install --namespace=demo2 --name demo2 --values ./helm/values.yaml ./helm