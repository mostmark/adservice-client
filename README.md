# adservice-client

This is a simple web application to test the adservice in the [Online Boutique](https://github.com/GoogleCloudPlatform/microservices-demo) microservices demo application.
This implementation uses Quarkus and the gRPC extension.

To deploy to OpenShift:

```
oc new-app java~https://github.com/mostmark/adservice-client.git -e quarkus.grpc.clients.adservice.host=adservice -l app.openshift.io/runtime=quarkus
oc expose service/adservice-client

```

To deploy this application from a prebuilt container image:

```
oc apply -f https://raw.githubusercontent.com/mostmark/adservice-client/refs/heads/main/deployment.yaml
```