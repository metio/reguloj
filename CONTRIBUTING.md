# Contributor Guide

Thank you so much for improving `reguloj`!
Without a healthy community, any open source project is doomed.

## Git Branching Model

The `main` branch always contains the latest public stable release.

## Issue Management

We are using the issue tracker over at [GitHub](https://github.com/metio/ilo/issues/) to track issues.

## Building the Project

Execute the following to build the project locally:

```shell
$ mvn verify
```

## Release Process

The release process is highly automated. It checks every week for changes in the `src/main/java` folder and creates a new timestamped release in case there were any changes.
