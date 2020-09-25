# campus-connect-api
Backend Repo for campus connect network

## Start DynamoDB Local Instance
### 1. Extract dynamodb_local_latest.rar to /somePath
### 2. Go to /somePath/dynamodb_local_latest
### 3. Open Command Prompt here
### 4. Run "java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb"
##### NOTE: If you want different port for dynamoDB use -port XXXX at the end of the previous command
#### This will start the local instance of DynamoDB at port 8000 (default)

## campus-social-media java project
### In applications.properties
#### Port, accesskeys, for dynamoDB etc are specified
#### Java project will start on port 8080
