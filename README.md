# Minikube

Aby wdrożyć aplikację w minikube trzeba uruchomić skrypt:  

`sh ./deploy-to-minikube.sh`

Skrypt wymaga zainstalowanego minikube.  
https://kubernetes.io/docs/setup/minikube/

Dodatkowo musi być zainstalowany helm.  
https://helm.sh

Do działania aplikacji w minikube musi być aktywny addon _kube-dns_
Aby zobaczyć listę aktywnych addonów, trzeba użyć polecenie:  
`minikube addons list`  

Zaleca się również sprawdzenie, czy pod _kube-dns_ jest uruchomiony:  
`kubectl -n kube-system get po`  

# Postman
Dla ułatwienia projektowania i ręcznego testowania w git znajduje się folder z plikami postman.  
https://www.getpostman.com/  
https://www.getpostman.com/docs/v6/postman/collections/data_formats