# Java Demo

## 二进制转换

## ArrayList

![ArrayList](./images/ArrayList.png)

### 概述

底层基于数组实现，并且实现了动态扩容

### RandomAccess

这是一个标记接口，内部是空的，标记"实现了这个接口的类支持快速（通常是固定时间）随机访问"。

快速随机访问是什么意思呢？就是说，不需要遍历，就可以通过下标（索引）直接访问到内存地址。

```java
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    E elementData(int index) {
        return (E) elementData[index];
    }
```

### Cloneable

ArrayList实现了Cloneable接口，这表明ArrayList是支持拷贝的。

ArrayList内部的确也重写了Object类的clone()方法。

```java
    public Object clone() {
        try {
            ArrayList<?> v = (ArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }
```

## LinkedList

