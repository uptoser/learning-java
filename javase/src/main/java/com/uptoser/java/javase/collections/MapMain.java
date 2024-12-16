package com.uptoser.java.javase.collections;

import java.util.*;

/**
 * Map有时也被称为字典
 * Map接口中定义了如下常用的方法。
 * ➢ void clear()：删除该Map对象中的所有key-value对。
 * ➢ boolean containsKey(Object key)：查询Map中是否包含指定的key，如果包含则返回true。
 * ➢ boolean containsValue(Object value)：查询Map中是否包含一个或多个value，如果包含则返回true。
 * ➢ Set entrySet()：返回Map中包含的key-value对所组成的Set集合，每个集合元素都是Map.Entry（Entry是Map的内部类）对象。
 * ➢ Object get(Object key)：返回指定key所对应的value；如果此Map中不包含该key，则返回null。
 * ➢ boolean isEmpty()：查询该Map是否为空（即不包含任何key-value对），如果为空则返回true。
 * ➢ Set keySet()：返回该Map中所有key组成的Set集合。
 * ➢ Object put(Object key, Object value)：添加一个key-value对，如果当前Map中已有一个与该key相等的key-value对，则新的key-value对会覆盖原来的key-value对。
 * ➢ void putAll(Map m)：将指定Map中的key-value对复制到本Map中。
 * ➢ Object remove(Object key)：删除指定key所对应的key-value对，返回被删除key所关联的value，如果该key不存在，则返回null。
 * ➢ boolean remove(Object key, Object value)：这是Java 8新增的方法，删除指定key、value所对应的key-value对。如果从该Map中成功地删除该key-value对，该方法返回true，否则返回false。
 * ➢ int size()：返回该Map里的key-value对的个数。
 * ➢ Collection values()：返回该Map里所有value组成的Collection。
 * Map接口提供了大量的实现类，典型实现如HashMap和Hashtable等、HashMap的子类LinkedHashMap，还有SortedMap子接口及该接口的实现类TreeMap，以及WeakHashMap、IdentityHashMap等。下面将详细介绍Map接口实现类。
 * Map中包括一个内部类Entry，该类封装了一个key-value对。Entry包含如下三个方法。
 * ➢ Object getKey()：返回该Entry里包含的key值。
 * ➢ Object getValue()：返回该Entry里包含的value值。
 * ➢ Object setValue(V value)：设置该Entry里包含的value值，并返回新设置的value值。
 */
public class MapMain {
    public static void main(String[] args) {
        new MapMain().hashMap().linkedHashMap().treeMap().weakHashMap().identityHashMap().enumMap();
    }

    /**
     * Java 8除为Map增加了remove（Object key，Object value）默认方法之外，还增加了如下方法。
     * ➢ Object compute(Object key, BiFunction remappingFunction)：
     * 该方法使用remappingFunction根据原key-value对计算一个新value。只要新value不为null，就使用新value覆盖原value；
     * 如果原value不为null，但新value为null，则删除原key-value对
     * 如果原value、新value同时为null，那么该方法不改变任何key-value对，直接返回null。
     * ➢ Object computeIfAbsent(Object key, Function mappingFunction)：如果传给该方法的key参数在Map中对应的value为null，
     * 则使用mappingFunction根据key计算一个新的结果，如果计算结果不为null，则用计算结果覆盖原有的value。如果原Map原来不包括该key，
     * 那么该方法可能会添加一组key-value对。
     * ➢ Object computeIfPresent(Object key, BiFunction remappingFunction)：如果传给该方法的key参数在Map中对应的value不为null，
     * 该方法将使用remappingFunction根据原key、value计算一个新的结果，如果计算结果不为null，则使用该结果覆盖原来的value；
     * 如果计算结果为null，则删除原key-value对。
     * ➢ void forEach(BiConsumer action)：该方法是Java 8为Map新增的一个遍历key-value对的方法
     * ➢ Object getOrDefault(Object key, V defaultValue)：获取指定key对应的value。如果该key不存在，则返回defaultValue。
     * ➢ Object merge(Object key, Object value, BiFunction remappingFunction)：该方法会先根据key参数获取该Map中对应的value。
     * 如果获取的value为null，则直接用传入的value覆盖原有的value（在这种情况下，可能要添加一组key-value对）；如果获取的value不为null，
     * 则使用remappingFunction函数根据原value、新value计算一个新的结果，并用得到的结果去覆盖原有的value。
     * ➢ Object putIfAbsent(Object key, Object value)：该方法会自动检测指定key对应的value是否为null，如果该key对应的value为null，
     * 该方法将会用新value代替原来的null值。
     * ➢ Object replace(Object key, Object value)：将Map中指定key对应的value替换成新value。与传统put()方法不同的是，
     * 该方法不可能添加新的key-value对。如果尝试替换的key在原Map中不存在，该方法不会添加key-value对，而是返回null。
     * ➢ boolean replace(K key, V oldValue, V newValue)：将Map中指定key-value对的原value替换成新value。
     * 如果在Map中找到指定的key-value对，则执行替换并返回true，否则返回false。
     * ➢ replaceAll(BiFunction function)：该方法使用BiFunction对原key-value对执行计算，并将计算结果作为该key-value对的value值。
     *
     * HashSet集合不能保证元素的顺序一样，HashMap、Hashtable也不能保证其中key-value对的顺序
     */
    public MapMain hashMap(){
        Map<String,String> maps = new HashMap<>();
        maps.put("name","小红");
        maps.put("age","18");
        maps.put("country","中国");
        maps.put("phone","1234567");
        maps.put(null,null);
        System.out.println("---------遍历Map1----------");
        for(Map.Entry<String,String> map: maps.entrySet()){
            System.out.println(map.getKey()+"-"+map.getValue());
        }
        System.out.println("---------遍历Map2----------");
        //通过 maps.keySet();  和 maps.values();
        System.out.println("---------遍历Map3----------");
        //通过迭代器
        Iterator<Map.Entry<String, String>> iterator = maps.entrySet().iterator();
        System.out.println("--------------------------");

        //如果新的 value 覆盖了原有的 value ，该方法返回被覆盖的 value
        System.out.println(maps.put("phone","8888888"));//输出1234567
        //是否包含ager的key
        System.out.println(maps.containsKey("age"));//true
        //是否包含555的value
        System.out.println(maps.containsValue("555"));//false
        //获取所有key组成的set集合
        Set<String> keySet = maps.keySet();
        System.out.println(keySet);
        //根据key删除键值对
        maps.remove("phone");
        System.out.println(maps);//phone被移除
        /*
        JDK8为Map新增的方法
         */
        maps.computeIfAbsent("phone",key -> key+0);
        System.out.println(maps);
        maps.merge("phone","00",(oldValue,param)->oldValue+param);
        System.out.println(maps);
        maps.replace("phone","phone000","123");
        System.out.println(maps);

        /*
        尽量少用Hashtable实现类，可以通过Collections工具类把HashMap变成线程安全的

        HashMap、Hashtable判断两个value相等的标准只要两个对象通过equals()方法比较返回true即可
         */
        return this;
    }

    /**
     * LinkedHashMap需要维护元素的插入顺序，因此性能略低于HashMap的性能；
     * 但因为它以链表来维护内部顺序，所以在迭代访问Map里的全部元素时将有较好的性能
     * @return
     */
    public MapMain linkedHashMap(){
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("北京",3);
        linkedHashMap.put("天津",2);
        linkedHashMap.put("重庆",7);
        System.out.println(linkedHashMap);

        return this;
    }

    /**
     * Properties类是Hashtable类的子类
     * ➢ String getProperty(String key)：获取Properties中指定属性名对应的属性值，类似于Map的get（Object key）方法。
     * ➢ String getProperty(String key, String defaultValue)：该方法与前一个方法基本相似。该方法多一个功能，
     * 如果Properties中不存在指定的key时，则该方法指定默认值。
     * ➢ Object setProperty(String key, String value)：设置属性值，类似于Hashtable的put()方法。
     * 除此之外，它还提供了两个读写属性文件的方法。
     * ➢ void load(InputStream inStream)：从属性文件（以输入流表示）中加载key-value对，
     * 把加载到的key-value对追加到Properties里（Properties是Hashtable的子类，它不保证key-value对之间的次序）。
     * ➢ void store(OutputStream out, String comments)：将Properties中的key-value对输出到指定的属性文件（以输出流表示）中。
     */
    public void properties(){

    }

    /**
     * 与TreeSet类似的是，TreeMap中也提供了一系列根据key顺序访问key-value对的方法。
     * ➢ Map.Entry firstEntry()：返回该Map中最小key所对应的key-value对，如果该Map为空，则返回null。
     * ➢ Object firstKey()：返回该Map中的最小key值，如果该Map为空，则返回null。
     * ➢ Map.Entry lastEntry()：返回该Map中最大key所对应的key-value对，如果该Map为空或不存在这样的key-value对，则都返回null。
     * ➢ Object lastKey()：返回该Map中的最大key值，如果该Map为空或不存在这样的key，则都返回null。
     * ➢ Map.Entry higherEntry(Object key)：返回该Map中位于key后一位的key-value对（即大于指定key的最小key所对应的key-value对）。
     * 如果该Map为空，则返回null。
     * ➢ Object higherKey(Object key)：返回该Map中位于key后一位的key值（即大于指定key的最小key值）。
     * 如果该Map为空或不存在这样的key-value对，则都返回null。
     * ➢ Map.Entry lowerEntry(Object key)：返回该Map中位于key前一位的key-value对（即小于指定key的最大key所对应的key-value对）
     * 如果该Map为空或不存在这样的key-value对，则都返回null。
     * ➢ Object lowerKey(Object key)：返回该Map中位于key前一位的key值（即小于指定key的最大key值）。
     * 如果该Map为空或不存在这样的key，则都返回null。
     * ➢ NavigableMap subMap(Object fromKey, boolean fromInclusive, Object toKey, boolean toInclusive)：
     * 返回该Map的子Map，其key的范围是从fromKey（是否包括取决于第二个参数）到toKey（是否包括取决于第四个参数）。
     * ➢ SortedMap subMap(Object fromKey, Object toKey)：返回该Map的子Map，其key的范围是从fromKey（包括）到toKey（不包括）。
     * ➢ SortedMap tailMap(Object fromKey)：返回该Map的子Map，其key的范围是大于fromKey（包括）的所有key。
     * ➢ NavigableMap tailMap(Object fromKey, boolean inclusive)：返回该Map的子Map，
     * 其key的范围是大于fromKey（是否包括取决于第二个参数）的所有key。
     * ➢ SortedMap headMap(Object toKey)：返回该Map的子Map，其key的范围是小于toKey（不包括）的所有key。
     * ➢ NavigableMap headMap(Object toKey, boolean inclusive)：返回该Map的子Map，
     * 其key的范围是小于toKey（是否包括取决于第二个参数）的所有key。
     * @return
     */
    public MapMain treeMap(){
        /**
         * TreeMap就是一个红黑树数据结构
         * TreeMap中判断两个key相等的标准是：两个key通过compareTo()方法返回0，TreeMap即认为这两个key是相等的
         *
         * 自然排序：TreeMap的所有key必须实现Comparable接口，而且所有的key应该是同一个类的对象，否则将会抛出ClassCastException异常。
         * 定制排序：创建TreeMap时，传入一个Comparator对象，该对象负责对TreeMap中的所有key进行排序。采用定制排序时不要求Map的key实现Comparable接口。
         */
        TreeMap<String,String> tm = new TreeMap<>();
        tm.put("q" , "q");
        tm.put("v" , "v");
        tm.put("a", "a");
        System.out.println(tm);
        //返回该TreeMap的第一个Entry对象
        System.out.println(tm.firstEntry());
        //返回该TreeMap的最后一个key值
        System.out.println(tm.lastKey());
        //返回该TreeMap的比a大的最小key值。
        System.out.println(tm.higherKey("a"));
        //返回该TreeMap的比q小的最大的key-value对。
        System.out.println(tm.lowerEntry("q"));
        //返回该TreeMap的子TreeMap
        System.out.println(tm.subMap("a" , "q"));
        return this;
    }

    /**
     * WeakHashMap与HashMap的用法基本相似。与HashMap的区别在于，HashMap的key保留了对实际对象的强引用
     * @return
     */
    public MapMain weakHashMap(){

        return this;
    }

    /**
     * 这个Map实现类的实现机制与HashMap基本相似，在IdentityHashMap中，当且仅当两个key严格相等（key1==key2）时，
     * IdentityHashMap才认为两个key相等
     * @return
     */
    public MapMain identityHashMap(){

        return this;
    }

    /**
     * EnumMap是一个与枚举类一起使用的Map实现，EnumMap中的所有key都必须是单个枚举类的枚举值。创建EnumMap时必须显式或隐式指定它对应的枚举类。
     * @return
     */
    public MapMain enumMap(){
        //创建一个EnumMap对象，该EnumMap的所有key
        //必须是Season枚举类的枚举值
        EnumMap enumMap = new EnumMap(Season.class);
        enumMap.put(Season.SUMMER , "夏日炎炎");
        enumMap.put(Season.SPRING , "春暖花开");
        System.out.println(enumMap);
        return this;
    }
    enum Season
    {
        SPRING,SUMMER,FALL,WINTER
    }


}
