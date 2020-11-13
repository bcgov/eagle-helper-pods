## Custom Eagle NSPs

quickstart-nsp.yaml is from https://github.com/BCDevOps/platform-services/blob/master/security/aporeto/docs/sample/quickstart-nsp.yaml and will allow  everything in the namespace to communicate freely.

to apply our custom network security policies, use:

```
oc process -f ./custom-eagle-nsp.yaml -p NAMESPACE=your-namespace | oc apply -f -
```