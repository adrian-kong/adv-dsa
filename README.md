# Data structures and algorithms

![badge](https://github.com/adrian-kong/adv-dsa/actions/workflows/ci.yaml/badge.svg)

## Motivation

This repo is motivated by my rusty java skills even though being my main language, the urge to implement my very own DSA
library to appreciate these underlying data structures and algorithms, being accustomed to importing without fully
understanding minor implementation details.

#### Mainly, this project is fuelled by a conversation in which demonstrated my inadequacy in teaching / conveying simple internal data structures coherently (how does a priority queue work? - which drives the Heap implementation)

## Requirements

- Java 19
- Maven

### Dependencies

All dependencies are optional

- JUnit (Testing framework)
- Project Lombok (Less boiler-plate = more readable)

## Implementations

GitHub Actions also included to verify some deterministic tests.

Comments are generally avoided for lean code

Since Java is not tail call optimized, implementations follow strictly an iterative approach (IIRC, [Church Turing
thesis](https://en.wikipedia.org/wiki/Church%E2%80%93Turing_thesis))

- Heap
    - [x] Binary Heap
    - [x] Binomial Heap
      - implemented using pointers + mostly mutating, maybe harder to read 
    - [ ] Fibonacci Heap
