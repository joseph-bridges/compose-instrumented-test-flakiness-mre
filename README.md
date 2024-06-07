This project is an MRE (minimal reproducible example) for a bug which is causing flakiness in Android instrumented tests when using compose. 

The issue can be reproduced by running the `testLoadingCompleted` test in the `ExampleInstrumentedTest` class which will occasionally fail with a 
`CalledFromWrongThreadException`.

An issue has been reported on the Google Issue Tracker with more details about this issue which can be found here - https://issuetracker.google.com/issues/321690042

## License

Any contributions made under this project will be governed by the
[Apache License 2.0](./LICENSE.txt).

## Code of Conduct

This project adheres to the [American Express Community Guidelines](./CODE_OF_CONDUCT.md). By
participating, you are expected to honor these guidelines.
