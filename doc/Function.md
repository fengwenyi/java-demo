# Function

```java
@FunctionalInterface
public interface Function<T, R> {
    
    R apply(T t);

}
```

T: 入参 <br>
R: 返参

简单示例：

平方函数

```java
public static Function<Integer, Integer> square() {
    return num -> num * num;
}
```

调用：

```java
int result = square().apply(5);
System.out.println(result);
```

为什么我们需要花费时间精力上手函数式编程而不是直接用面对对象解决问题？

- 函数式编程的引入，帮助我们减少业务逻辑和代码的分歧。他允许我们在更高层次更自然的描述业务逻辑。让代码直接描述“你想做什么”，而不是“你想怎样去做”。
- 许多样板（boilerplates）可以被移除，这会让代码更清晰更简洁。
- 高阶函数（Higher-order functions）允许我们：　　 
  - 发送方法到其他方法中。
  - 在其他方法中创建方法。
  - 从其他方法中返回方法。