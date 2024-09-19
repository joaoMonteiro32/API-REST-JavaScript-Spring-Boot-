#!/bin/bash

./mvnw clean package

sudo docker build -t api-rest:2.0 .

IMAGE_NAME="a51762/api-rest"
IMAGE_ID=$(docker images --format "{{.ID}}" --filter=reference="$IMAGE_NAME")

if [ -n "$IMAGE_ID" ]; then
    echo "Found image ID: $IMAGE_ID"

    docker rmi "$IMAGE_ID"

    echo "Image $IMAGE_NAME with ID $IMAGE_ID has been removed."
else
    echo "No image found with the name $IMAGE_NAME"
fi

sudo docker tag api-rest:2.0 a51762/api-rest:2.0

sudo docker push a51762/api-rest:2.0
