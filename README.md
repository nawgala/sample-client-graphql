# README 

This doc will explain you following 
1. How to build this project
1. How to generate sample client for graphql api

## Environment

* java 1.8
* Gradle 5.6.4

## How to build this project
   
Run `./gradlew clean build`. This is generate required client source codes.  
The generated source code is in `build/generated/source/apollo/main/service`. 
And  the build process will generate lib at `build/libs/sample-client-graphql-0.0.1-SNAPSHOT.jar`.
All the generated resource are compiled and added  to above library by the build cycle.
So, You can use that library in your project as a dependency.

please see example given in `SampleClientGraphqlApplication`



## How to generate sample client for graphql api
1. install apollo from `https://github.com/apollographql/apollo-tooling`

1. Run `apollo client:extract --endpoint=http://127.0.0.1:8080/graphql` to extract graphql queries from the graphql endpint. 
   It will give you `manifest.json` file that contains all the queries that are available in graphql api. 
   
   sample has added to `src/main/graphql/manifest.json`
   
1. Your are required to create separate file  with `graphql` extension by extracting queries from`manifest.json`

Ex: 

        {
          "version": 2,
          "operations": [
            {
              "signature": "c6b3da5fbf75347e87c81483683b668cf92792e4cbf5afa12b5282f65c7a8afe",
              "document": "mutation GetApiAccessToken($clientId:String!,$secret:String!){getApiAccessToken(clientId:$clientId,secret:$secret){__typename access_token expires_in refresh_token scope token_type}}",
              "metadata": {
                "engineSignature": ""
              }
            },
    
  create a separate file say, `GetApiAccessToken.graphql` in `src/main/graphql`  and copy value of `document`. 
    
        mutation GetApiAccessToken($clientId:String!,$secret:String!){getApiAccessToken(clientId:$clientId,secret:$secret){__typename access_token expires_in refresh_token scope token_type}}                    
        
  Remove `__typename` and format it as We have done in `src/main/graphql/mutations/GetApiAccessToken.graphql` . Follow same stpes for rest of the queries too. 
  It is better to have two separeate name spaces for queries and mutations.
1. Run `apollo client:download-schema  --endpoint=http://127.0.0.1:8080/graphql` to download `schema.json` and add it to 
   `src/main/graphql`
1. Finally run `./gradlew build` to generate source and build the project   
   

