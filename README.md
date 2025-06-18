# 

## Model
www.msaez.io/#/215609985/storming/5abefefc0c55b4b301175951e26de058

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd infra
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- OrderManagement
- Delivery
- RestaurantMenu
- UserAccessControl
- StatisticsReporting


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- OrderManagement
```
 http :8088/orders orderId="orderId"customerId="customerId"deliveryPersonId="deliveryPersonId"orderTime="orderTime"deliveryAddress="deliveryAddress"totalAmount="totalAmount"cancelReason="cancelReason"
 http :8088/reviews reviewId="reviewId"customerId="customerId"rating="rating"content="content"createdAt="createdAt"
 http :8088/carts cartId="cartId"customerId="customerId"createdAt="createdAt"
```
- Delivery
```
 http :8088/deliveries deliveryId="deliveryId"lastLocationUpdate="lastLocationUpdate"
 http :8088/riders riderId="riderId"
```
- RestaurantMenu
```
 http :8088/restaurants restaurantId="restaurantId"
```
- UserAccessControl
```
 http :8088/users userId="userId"name="name"phoneNumber="phoneNumber"grade="grade"registeredAt="registeredAt"suspendedReason="suspendedReason"
 http :8088/phoneVerifications verificationId="verificationId"phoneNumber="phoneNumber"verified="verified"verifiedAt="verifiedAt"
```
- StatisticsReporting
```
 http :8088/reports reportId="reportId"generatedBy="generatedBy"createdAt="createdAt"
 http :8088/reportData reportDataId="reportDataId"value="value"recordDate="recordDate"
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```
