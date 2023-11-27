# Fearless Concurrency 

> Initially, the Rust team thought that ensuring memory safety and preventing concurrency problems were two separate challenges to be solved with different methods. Over time, the team discovered that the ownership and type systems are a powerful set of tools to help manage memory safety and concurrency problems! By leveraging ownership and type checking, many concurrency errors are compile-time errors in Rust rather than runtime errors.
>
>([The Rust Programming Language](https://doc.rust-lang.org/book/ch16-00-concurrency.html))


This is a take on _Rust's fearless concurrency_ by an example of counting prime numbers. It explores _buggy_ implementations in 

- [C++](prime-cpp)
- [CSharp](prime-csharp)
- [Kotlin](prime-kotlin)
- [Rust](prime-rust)

and explores if and how the _compiler_ helps preventing these bugs.

Each folder can be opened with [VS Code](https://code.visualstudio.com) and contains a task to build `Task: Run Build Task` and task to then run tests `Task: Run Test Task`.