1. 默认大小为10

2. 通过对象数组实现，有两个静态空数组作为通用的空对象返回。区别在于是否是默认大小

3. new ArrayList实现：

   ```java
   public ArrayList(int initialCapacity) {
       if (initialCapacity > 0) {
           this.elementData = new Object[initialCapacity];
       } else if (initialCapacity == 0) {
           this.elementData = EMPTY_ELEMENTDATA;
       } else {
           throw new IllegalArgumentException("Illegal Capacity: "+initialCapacity);
       }
   }
   ```

   ```java
   public ArrayList() {
       this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
   }
   ```

   ```java
   public ArrayList(Collection<? extends E> c) {
       elementData = c.toArray();
       if ((size = elementData.length) != 0) {
           // c.toArray might (incorrectly) not return Object[] (see 6260652)
           if (elementData.getClass() != Object[].class)
               elementData = Arrays.copyOf(elementData, size, Object[].class);
       } else {
           this.elementData = EMPTY_ELEMENTDATA;
       }
   }
   ```

4. trimToSize 引起结构变化，modCount++

5. index遍历数组实现，能够查找null，非null通过equals方法判断

6. toArray

   ```java
   public Object[] toArray() {
       return Arrays.copyOf(elementData, size);
   }
   ```

   ```java
   public <T> T[] toArray(T[] a) {
       if (a.length < size)
           // Make a new array of a's runtime type, but my contents:
           return (T[]) Arrays.copyOf(elementData, size, a.getClass());
       System.arraycopy(elementData, 0, a, 0, size);
       if (a.length > size)
           a[size] = null;
       return a;
   }
   ```

7. get方法中，通过私有方法rangeCheck检验index是否合法，但这个方法里之校验了index>=size的情况，没有index<0的情况，而是在执行elementData[i]时抛出异常，然而rangeCheckForAdd中的校验，则包含了这两种

8. 扩容

9. add，remove，clear，retain，iterator，serialize