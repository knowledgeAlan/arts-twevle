# WeakHashMap 详解

## 1.环境

jdk8

## 1.1介绍

本文讨论java.util.package下[WeakHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/WeakHashMap.html) ，为了更好理解数据结构，使用它可以实现简单缓存，并不建议线上环境使用它来，这里只是来帮助理解weakHashmap原理，`WeakHashMap` 是用哈希表实现 `Map`接口，key是`WeekReference`类型，`Entry`在`WeakHashMap`被自动删除当`key`不在被使用时候，意味着`key`对象没有被引用，垃圾回收线程会回收`key`对象，`Entry`会从map中移除，因此`WeakhHashMap`是另外一种实现`Map`接口

## 2.强引用 软引用 和弱引用

为了更好理解`WeakHashMap`，需要理解弱引用，`put()`方法中会把key变成弱引用，java中主要有3中引用，后面部分会介绍

###2.1.强引用是最常的引用方式

```java
Integer prime = 1
```

变量prime强引用Integer对象为1值，任何对象被强引用不会被垃圾回收掉

###2.2.软引用

一个对象是软引用不会被垃圾回收器回收直到jvm虚拟机内存不够时候，下面创建软引用案例:

```java
Integer prime = 1;
SoftReference<Integer> soft = new SoftReference(prime);
prime = null;
```

prime对象是强引用，后面把强引用变成软引用，最后prime强引用至为空，prime对象会被垃圾回收当jvm需要内存时候

### 2.3.  弱引用

弱引用对象会被垃圾回收立即，垃圾回收不会等到需要内存时候，下面弱引用案例:

```java
Integer prime = 1;
WeakReference<Integer> integerWeakReference = new WeakReference<>(prime);
prime = null;
```

prime设置为空，prime对象将会被垃圾回收在下个垃圾回收周期中，因为这里没有其他强引用指向它

弱引用作为key存在`WeakHashmap`中

## 3. WeakHashmap当做高效缓存

建立缓存保存大量图片对象作为值，图像名称作为key，想用一个合适`Map` 来解决这个问题，使用`HashMap`不是好的选择，因为对象值会占用很大内存，垃圾回收时候不会被回收，在不使用时候不会被回收，然而，我们想用`Map`接口

能帮我们自动释放内存当key不被使用时候，刚好`WeakHashMap`具备这个特点，下面是案例:

```java
WeakHashMap<UniqueImageName, BigImage> map = new WeakHashMap();
BigImage bigImage = new BigImage("image_id");
UniqueImageName imageName = new UniqueImageName("name_of_big_image");

map.put(imageName, bigImage);
assertTrue(map.containsKey(imageName));

imageName = null;
System.gc();

await().atMost(10, TimeUnit.SECONDS).until(map::isEmpty);
```

我们创建`WeakHashMap` 实例存储BigImage对象，把BigImage对象作为值和imagename作为key，imageName是弱引用存在`Map`中，接下来设置imageName为空，因此没有更多人指向bigImage对象，WeakHashMap默认会删除key在下个垃圾回收阶段

我也可以调用System.gc()强制触发垃圾回收：

```java
WeakHashMap<UniqueImageName, BigImage> map = new WeakHashMap();
BigImage bigImageFirst = new BigImage("foo");
UniqueImageName imageNameFirst = new UniqueImageName("name_of_big_image");

BigImage bigImageSecond = new BigImage("foo_2");
UniqueImageName imageNameSecond = new UniqueImageName("name_of_big_image_2");

map.put(imageNameFirst, bigImageFirst);
map.put(imageNameSecond, bigImageSecond);

assertTrue(map.containsKey(imageNameFirst));
assertTrue(map.containsKey(imageNameSecond));

imageNameFirst = null;
System.gc();

await().atMost(10, TimeUnit.SECONDS)
  .until(() -> map.size() == 1);
await().atMost(10, TimeUnit.SECONDS)
  .until(() -> map.containsKey(imageNameSecond));
```

注意imageNameFirst引用至为空，imageNameSecond引用保持不变，垃圾回收之后，`map`仅存在imageNameSecond

## 4.总结

本文讨论java中引用，理解`WeakHashMap` 原理，创建一个简单缓存用WeakhashMap来改变缓存对象

[参考地址](https://www.baeldung.com/java-weakhashmap)