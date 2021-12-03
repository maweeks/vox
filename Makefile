help:          ## Show this help.
	@fgrep -h "##" $(MAKEFILE_LIST) | fgrep -v fgrep | sed -e 's/\\$$//' | sed -e 's/##//'

env:           ## List the environment versions
	which java
	java --version

test:          ## Run the tests for this prject
	mvn test

install:       ## Install the required imports for this project
	mvn install
