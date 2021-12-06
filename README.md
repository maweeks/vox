# Initial Java

Repo for VoxSmart tech task.

## Makefile

There are a number of make targets to help run things.

```Makefile
help:           Show this help.
env:            List the environment versions
test:           Run the tests for this prject
install:        Install the required packages for this project
```

## Getting started

Prerequisites:

- Java
- Maven

Running the tests:

- Can run `make env` to check java and maven installation.
- Use `make install` to install the required packages
- Use `make test` to run the tests

## Assumptions

- `userNumber` always contains country code
- `userNumber` country code is always in the supplied list of country codes
- All countries in `countryCodes` will also be contained in the supplied `nationalTrunkPrefixes`
